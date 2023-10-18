package com.example.smartcity.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RecCommentBean
import com.example.smartcity.bean.RecNewsBean
import com.example.smartcity.databinding.ActivityRecNewsBinding
import com.example.smartcity.databinding.ItemNewsCommentBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class RecNewsActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRecNewsBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.recNewsTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id", 0)
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        点击弹出软键盘
        vb.recNewsCommit.setOnClickListener {
            imm.showSoftInput(vb.recNewsCommit, InputMethodManager.SHOW_IMPLICIT)
        }
//        加载新闻详情
        tool.apply {
            send("/prod-api/api/garbage-classification/news/$id", "GET", null, true) {
//                通过事件触发软键盘弹出
                imm.hideSoftInputFromWindow(vb.recNewsCommit.windowToken, 0)
                val data = g.fromJson(it, RecNewsBean::class.java).data
                vb.recNewsTitle.text = data.title
                vb.recNewsWb.loadDataWithBaseURL(null, data.content, "html/text", "utf-8", null)
            }
        }
        loadComment(id)
        submitComment(id,imm)
    }

    private fun submitComment(id: Int,imm :InputMethodManager) {
        vb.recNewsBtn.setOnClickListener {
            val data = """
                {
                "newsId": $id,
                "content": "${vb.recNewsCommit.text}"
                }  
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/garbage-classification/news-comment","POST",req,true) {
                    if (it.contains("操作成功")) {
                        loadComment(id)
                        imm.hideSoftInputFromWindow(vb.recNewsCommit.windowToken, 0)
                        vb.recNewsCommit.clearFocus()
                        vb.recNewsCommit.setText("")
                        Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun loadComment(id: Int) {
        tool.apply {
            send(
                "/prod-api/api/garbage-classification/news-comment/list?newsId=${id}",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(
                    it,
                    RecCommentBean::class.java
                ).rows.sortedByDescending { it.createTime }
                vb.recInfoCommentNumList.adapter = GenericAdapter(data.size,
                    { ItemNewsCommentBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.itemNewsCommentUserName.text = data[position].userId.toString()
                    binding.itemNewsCommentContent.text = data[position].content
                    binding.itemNewsCommentCommentDate.text = data[position].createTime
                }
                vb.recInfoCommentNumList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}