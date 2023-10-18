package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityFeedbackBinding
import com.example.smartcity.tool
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

class FeedbackActivity : AppCompatActivity() {
    lateinit var vb: ActivityFeedbackBinding
    private val TAG = "FeedbackActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.feedbackTb.setOnClickListener {
            finish()
        }
//      TODO("意见反馈列表和反馈详情还未完成 在意见反馈页面加两个模块根据查询id显示列表点击列表查看详细信息")
        vb.feedbackList.setOnClickListener {
            tool.apply {
                Toast.makeText(context,"等下做",Toast.LENGTH_SHORT).show()
            }
        }
        vb.feedbackCommit.setOnClickListener {
//            构建一个绑定上下文
            vb.apply {
                val title = feedbackTitle.text
                val content = feedbackContent.text
                val data = """
                    {
                    "title":"$title",
                    "content":"$content"
                    }
                """.trimIndent()
                val body = data.toRequestBody("application/json".toMediaTypeOrNull())
                tool.apply {
                    send("/prod-api/api/common/feedback", "POST", body, true) {
                        val obj = JSONObject(it)
                        try {
                            Toast.makeText(context, obj.getString("msg"), Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Log.e(TAG, "${e.message}")
                        }
                        if (obj.getString("msg") == "操作成功") {
                            finish()
                        }
                    }
                }
            }
        }
    }
}