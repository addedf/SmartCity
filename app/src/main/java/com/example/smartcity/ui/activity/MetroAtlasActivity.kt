package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.MetroAtlasBean
import com.example.smartcity.databinding.ActivityMetroAtlasBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class MetroAtlasActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMetroAtlasBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.metroAtlasTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/metro/city","GET",null,false) {
                val data = g.fromJson(it,MetroAtlasBean::class.java).data
                Glide.with(context).load(getUrl(data.imgUrl)).into(vb.metroAtlasImgUrl)
            }
        }
    }
}