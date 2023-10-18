package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.smartcity.bean.LibraryInfoBean
import com.example.smartcity.databinding.ActivityLibraryInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class LibraryInfoActivity : AppCompatActivity() {
//    by属性委托 它接受一个带有 inflate作为参数不需要在下面在重写赋值页面资源
    private val vb by viewBinding (ActivityLibraryInfoBinding::inflate )
    private val TAG = "LibraryInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.liTb.setOnClickListener {
            finish()
        }
//        接收传递过来的id使用一个默认值占位
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/digital-library/library/${id}","GET",null,true) {
                val data = g.fromJson(it, LibraryInfoBean::class.java).data
//                一次只展示一个界面不需要适配器
                vb.liAddress.text = data.address
                vb.liBusinessHours.text = data.businessHours
                vb.liName.text = data.name
                vb.liDescription.text = data.description
                vb.liBusinessState.text = when(data.businessState) {
                    "0" -> "暂停营业"
                    else -> "正常营业"
                }
                Glide.with(context).load(getUrl(data.imgUrl)).transform(CenterCrop()).into(vb.liImg)
            }
        }
    }
}