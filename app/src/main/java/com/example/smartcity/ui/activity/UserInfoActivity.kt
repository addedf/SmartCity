package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.bean.UserInfoBean
import com.example.smartcity.databinding.ActivityUserInfoBinding
import com.example.smartcity.tool
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class UserInfoActivity : AppCompatActivity() {
    lateinit var vb : ActivityUserInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(vb.root)
//        设置性别变量
        var sex = "0"
        vb.userInfoTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/common/user/getInfo","GET",null,true) {
                val user = Gson().fromJson(it,UserInfoBean::class.java).user
//                设置显示字段文本
                vb.userInfoEmail.setText(user.email)
                vb.userInfoPhone.setText(user.phonenumber)
                vb.userInfoNickname.setText(user.nickName)
                if (user.sex == "0") {
//                    设置选中
                    vb.userInfoMan.isChecked = true
                } else if (user.sex == "1") {
                    vb.userInfoWoman.isChecked = true
                }
                vb.userInfoMan.setOnClickListener{
                    sex = "0"
                }
                vb.userInfoWoman.setOnClickListener{
                    sex = "1"
                }
//                点击提交当前信息
                vb.userInfoBtn.setOnClickListener {
                    val data = """
                        {
                        "email":"${vb.userInfoEmail.text}",
                        "idCard":"${user.idCard}",
                        "nickName":"${vb.userInfoNickname.text}",
                        "phonenumber":"${vb.userInfoPhone.text}",
                        "sex":"$sex"
                        }
                    """.trimIndent()
                    val body = data.toRequestBody("application/json".toMediaTypeOrNull())
                    send("/prod-api/api/common/user","PUT",body,true) { it ->
                        if(it.contains("操作成功")){
                            finish()
                        }else{
                            Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}