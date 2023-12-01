package com.example.smartcity.ui.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.webkit.WebSettings
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.EventdCommentBean
import com.example.smartcity.databinding.ActivityEventsInfoBinding
import com.example.smartcity.databinding.ItemEventsCommentBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

class EventsInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityEventsInfoBinding::inflate)
    val TAG = "EventsInfoActivity"
    private val newsId: Int by lazy {
        intent.getIntExtra("id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.evenInfoTb.setOnClickListener {
            finish()
        }
//        禁止软键盘弹出
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        vb.evenInfoInput.setOnClickListener {
            imm.showSoftInput(vb.evenInfoInput, InputMethodManager.SHOW_IMPLICIT)
        }
//        接收传递的数据
        val likeNum = intent.getStringExtra("likeNum")
        val createTime = intent.getStringExtra("createTime")
        val name = intent.getStringExtra("name")
        val content = intent.getStringExtra("content")
//        绑定数据
        vb.evenInfoCreateTime.text = "创建时间:${createTime}"
        vb.evenInfoLikeNum.text = "参与人数:${likeNum}"
        vb.evenInfoName.text = name
        vb.evenInfoWeb.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        vb.evenInfoWeb.loadDataWithBaseURL(null, content!!, "html/text", "utf-8", null)
//        加载评论
        loadComment(imm)
        submitComment(imm)
//        点击评论
        vb.evenBtn.setOnClickListener {
            val data = """
                {
                "activityId":$newsId
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/activity/signup", "POST", data, true) { it1 ->
//                                区分开两个不同的it 一个是本作用域的返回值，一个是上一个作用域的是否能报名的状态
                    if (it1.contains("操作成功")) {
                        Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show()
                        it.isEnabled = false
                        (it as Button).text = "已报名"
                    } else {
                        try {
                            Toast.makeText(
                                context,
                                JSONObject(it1).getString("msg"),
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (_: Exception) {
                            Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun submitComment(imm:InputMethodManager) {
        vb.evenInfoBtn.setOnClickListener {
            val data = """
                {
                "activityId":$newsId,
                "content":"${vb.evenInfoInput.text}"
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/activity/comment", "POST", data, true) {
                    if (it.contains("操作成功")) {
//                        提交成功在加载一次评论
                        loadComment(imm)
//                       清空文本值
                        //            收起软键盘
                        imm.hideSoftInputFromWindow(vb.evenInfoInput.windowToken, 0)
                        vb.evenInfoInput.clearFocus()
                        vb.evenInfoInput.setText("")
                        Toast.makeText(context, "评论成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun loadComment(imm:InputMethodManager) {
        imm.hideSoftInputFromWindow(vb.evenInfoInput.windowToken, 0)
        tool.apply {
            send(
                "/prod-api/api/activity/comment/list?activityId=${newsId}",
                "GET",
                null,
                false
            ) { it ->
                val data = g.fromJson(
                    it,
                    EventdCommentBean::class.java
                ).rows.sortedByDescending { it.createTime }
                vb.evenInfoCommentNumList.adapter = GenericAdapter(data.size,
                    { ItemEventsCommentBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemNewsCommentContent.text = data[position].content
                    binding.itemNewsCommentNickName.text = data[position].nickName
                    binding.itemNewsCommentCreateTime.text = "评论时间：" + data[position].createTime
                    Glide.with(context).load(data[position].avatar?.let { it1 -> getUrl(it1) })
                        .error(getDrawable(R.drawable.chengshi))
                        .transform(CenterCrop(), RoundedCorners(5.dp))
                        .into(binding.itemEvenCommentAvatar)
                }
                vb.evenInfoCommentNumList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}