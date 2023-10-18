package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.bean.VoInfoBean
import com.example.smartcity.databinding.ActivityVoInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class VoInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityVoInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val id = intent.getIntExtra("id",0)
        vb.viTb.setNavigationOnClickListener { finish() }
        tool.send("/prod-api/api/volunteer-service/activity/${id}","GET",null,true){
            val data = g.fromJson(it, VoInfoBean::class.java).data
            println(data)
            vb.viName.text = data.title
            vb.viStart.text = data.startAt
            vb.viDetail.text = data.detail
            vb.viRequire.text = data.requireText
            vb.viUndertaker.text = data.undertaker
        }
    }
}