package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityPetInquiryInfoBinding
import com.example.smartcity.dp
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import java.lang.Exception

class PetInquiryInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetInquiryInfoBinding::inflate)
    val TAG = "PetInquiryInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.petInpInfoTb.setOnClickListener {
            finish()
        }
        val goodAt = intent.getStringExtra("goodAt")
        val jobName = intent.getStringExtra("jobName")
        val name = intent.getStringExtra("name")
        val practiceNo = intent.getStringExtra("practiceNo")
        val avatar = intent.getStringExtra("avatar")
        val question = intent.getStringExtra("question")
        val imageUrls = intent.getStringExtra("imageUrls")

//        绑定数据
        tool.apply {
            try {
                Glide.with(context).load(imageUrls?.let { getUrl(it) })
                    .error(getDrawable(R.drawable.chengshi))
                    .transform(CenterCrop(),RoundedCorners(5.dp))
                    .into(vb.petInpInfoImageUrls)
                Glide.with(context).load(avatar?.let { getUrl(it) })
                    .error(getDrawable(R.drawable.chengshi))
                    .transform(CenterCrop(),RoundedCorners(5.dp))
                    .into(vb.petInpInfoAvatar)
            } catch (e:Exception) {
                Log.e(TAG, "${e.message}")
            }
            vb.petInpInfoGoodAt.text = "擅长领域:$goodAt"
            vb.petInpInfoName.text = "主治医师:$name"
            vb.petInpInfoJobName.text = "职称:$jobName"
            vb.petInpInfoPracticeNo.text = practiceNo
            vb.petInpInfoQuestion.text = "问题描述:$question"
        }
    }
}