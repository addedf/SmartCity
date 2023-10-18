package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.smartcity.bean.ExpressInfoBean
import com.example.smartcity.databinding.ActivityExpressInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool

class ExpressInfoActivity : AppCompatActivity() {
    lateinit var vb : ActivityExpressInfoBinding
    val TAG = "ExpressInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityExpressInfoBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.expressInfoTb.setOnClickListener {
            finish()
        }
//        验证是否登录
        tool.checkToken {
            if (it) {
//                公司信息
                loadInfo()
            } else {
                Toast.makeText(this,"未登录", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // TODO("投诉和投诉记录暂时不用做请求不到数据 但是查询单号需要做")
    private fun loadInfo() {
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/logistics-inquiry/logistics_company/$id","GET",null,true) {
                val data = g.fromJson(it, ExpressInfoBean::class.java).data
                vb.expressInfoIntroduce.text = data.introduce.replace("\n","")
                vb.expressInfoMethod.text = data.shippingMethod
                vb.expressInfoPhone.text = data.phone
                vb.expressInfoName.text = data.name
                try{
                    Glide.with(context).load(getUrl(data.imgUrl)).circleCrop().into(vb.expressInfoIcon)
                }catch (e: Exception){
                    Log.e(TAG, "${e.message}")
                }
            }
        }
    }
}