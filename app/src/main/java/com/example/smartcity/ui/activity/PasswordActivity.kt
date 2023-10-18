package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityPasswordBinding
import com.example.smartcity.tool
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class PasswordActivity : AppCompatActivity() {
    lateinit var vb : ActivityPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityPasswordBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.passwordTa.setOnClickListener {
            finish()
        }
        vb.passwordCommit.setOnClickListener {
            val oldPw = vb.passwordOld.text
            val newPw = vb.passwordNew.text
            val data = """
                {
                "newPassword":"$newPw",
                "oldPassword":"$oldPw"
                }
            """.trimIndent()
            val body = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/common/user/resetPwd","PUT",body,true) {
                    val obj = JSONObject(it)
                    if (obj.getString("msg") == "操作成功") {
                        Toast.makeText(context,"操作成功", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(context,obj.getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}