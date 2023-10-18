package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {
    lateinit var vb : ActivityNewsBinding
    private val TAG = "NewsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(vb.root)
//        接收点击传递的数据
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        vb.newsTbTitle.text = title
        vb.newsBack.setOnClickListener {
            finish()
        }
//        允许加载混合内容
        vb.newsWv.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//        设置加载内容 不需要url 内容变量 文本html 解码格式 没有历史数据
        vb.newsWv.loadDataWithBaseURL(null, content!!, "html/text", "utf-8",null)
    }
}