package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.bean.VolunteerkNewsBean
import com.example.smartcity.databinding.ActivityVolunteerkNewsBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class VolunteerkNewsActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityVolunteerkNewsBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.vonNewsTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/volunteer-service/news/${id}","GET",null,true) {
                val data = g.fromJson(it, VolunteerkNewsBean::class.java).data
                vb.itemVonNewsTitle.text = data.title
                Glide.with(context).load(getUrl(data.imgUrl))
                    .transform(CenterCrop(),RoundedCorners(5.dp))
                    .into(vb.itemVonNewsImg)
                val reg = "<[^<]+?>|<p>|&nbsp;|</p>|<p>[\\\\s\\\\S]*?</p>|&nbsp;"
                vb.itemVonNewsContent.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                vb.itemVonNewsContent.loadDataWithBaseURL(null, data.content, "html/text", "utf-8",null)
            }
        }
    }
}