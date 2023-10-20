package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivitySubmitLaimBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class SubmitLaimActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivitySubmitLaimBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.subTb.setOnClickListener {
            finish()
        }
        vb.subBtn.setOnClickListener {
            val data = """
                {
                   "appealCategoryId": 5,
                   "title": "${vb.subTitle.text}",
                    "content": "${vb.subContent.text}",
                    "undertaker": "${vb.subUndertaker.text}",
                    "imgUrl": "/profile/abc.png"
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/gov-service-hotline/appeal", "POST", req, true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context, "提交成功", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }
}