package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityLoginBinding
import com.example.smartcity.tool
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    val TAG = "LoginActivity"
    lateinit var vb: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.loginTb.setOnClickListener {
            finish()
        }
//        点击注册
        vb.zXText.setOnClickListener {
//            上下文挂载在了Tool上所以使用需要调用
            tool.apply {
                Toast.makeText(context,"注册等下再做",Toast.LENGTH_SHORT).show()
            }
        }
//        点击登录
        vb.loginBtn.setOnClickListener {
            val username = vb.loginUsername.text
            val password = vb.loginPassword.text
            val data = """
                {
                "username":"$username",
                "password":"$password"
                }
                """.trimIndent()
            Log.e(TAG, "$data")
//            toRequestBody 是一个 OkHttp 提供的扩展函数，它接收一个媒体类型作为参数，并将当前对象转换为对应的请求体。在这种情况下，data 对象必须是一个字符串或字节数组 然后指定发送的数据类型
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())

            tool.apply {
                send("/prod-api/api/login","POST",req,false) {
//                    把it登录成功返回数据转换成json JSONObject可以输入字段返回值 也可以以键值对的形式添加数据
                    val obj = JSONObject(it)
//                    判断字符串是返回操作成功
                    if (it.contains("操作成功")) {
//                        写入登录成功返回的token
                        set("token",obj.getString("token"))
                        finish()
                    } else {
                        Toast.makeText(context,obj.getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}