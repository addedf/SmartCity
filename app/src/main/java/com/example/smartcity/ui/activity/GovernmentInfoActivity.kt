package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityGovernmentInfoBinding
import com.example.smartcity.viewBinding

class GovernmentInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityGovernmentInfoBinding::inflate)
    val TAG = "GovernmentInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.govInfoTb.setOnClickListener {
            finish()
        }
        val createTime = intent.getStringExtra("createTime")
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        val undertaker = intent.getStringExtra("undertaker")
        val appealCategoryName = intent.getStringExtra("appealCategoryName")
        vb.govInfoText.text = appealCategoryName
        vb.govInfoContent.text = content
        vb.govInfoCreateTime.text = createTime
        vb.govInfoTitle.text = title
        vb.govInfoUndertaker.text = undertaker
        vb.govInfoBtn.setOnClickListener {
            startActivity(Intent(this,SubmitLaimActivity::class.java))
        }
    }
}