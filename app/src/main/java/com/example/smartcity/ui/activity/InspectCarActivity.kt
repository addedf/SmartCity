package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.*
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.databinding.ActivityInspectCarBinding
import com.example.smartcity.dialog.InspectCarDialog
import com.youth.banner.Banner

class InspectCarActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityInspectCarBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.insCarTb.setOnClickListener {
            finish()
        }
        InspectCarDialog(this).show()
        tool.checkToken {
            if (it) {
                loadBanner()
                vb.insCarManage.setOnClickListener {
                    jump(CarManageActivity::class.java)
                }
                vb.insCarOrder.setOnClickListener {
                    jump(CarOrderActivity::class.java)
                }
            } else {
                tool.snackbar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/traffic/rotation/list","GET",null,false) {
                val data = g.fromJson(it,BannerBean::class.java).rows
                val list = mutableListOf<String>()
                for (datum in data.indices) {
                    list.add(data[datum].advImg)
                }
                setBanner(vb.insCarBanner,list)
            }
        }
    }
}