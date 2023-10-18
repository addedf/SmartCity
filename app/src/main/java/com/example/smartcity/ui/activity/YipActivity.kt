package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.smartcity.R
import com.example.smartcity.bean.YipBean
import com.example.smartcity.databinding.ActivityYipBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class YipActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityYipBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.yipTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/youth-inn/talent-policy/$id","GET",null,true) {
                val data = g.fromJson(it,YipBean::class.java).data
                if (data!=null) {
                    vb.yipTitle.text = data.title
                    vb.yipWv.loadDataWithBaseURL(null,data.content, "html/text", "utf-8", null)
                    vb.yipWv.visibility = View.VISIBLE
                    vb.yipTip.visibility = View.GONE
                } else {
                    vb.yipTitle.text = "人才政策"
                    vb.yipWv.visibility = View.GONE
                    vb.yipTip.visibility = View.VISIBLE
                }
            }
        }
    }
}