package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.databinding.ActivityInspectCarBinding
import com.example.smartcity.dialog.InspectCarDialog
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
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
        loadBanner()
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