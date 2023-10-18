package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcity.bean.RecBannerBean
import com.example.smartcity.databinding.ActivityGarbageSortingBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class GarbageSortingActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityGarbageSortingBinding::inflate)
    val TAG = "GarbageSortingActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.recTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                vb.recSearch.setOnClickListener {
                    startActivity(Intent(this,RecSearchActivity::class.java))
                }
                vb.recClazz.setOnClickListener {
                    startActivity(Intent(this,RecClazzActivity::class.java))
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
            send("/prod-api/api/garbage-classification/ad-banner/list","GET",null,true) {
                val data = g.fromJson(it, RecBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                Log.e(TAG, "$list")
                setBanner(vb.recBanner,list)
            }
        }
    }
}