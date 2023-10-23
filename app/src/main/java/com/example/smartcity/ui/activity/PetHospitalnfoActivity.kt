package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityPetHospitalnfoBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class PetHospitalnfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetHospitalnfoBinding::inflate)
    val TAG = "PetHospitalnfoActivity"
    private val fromAlbum = 2
//    lateinit var bitmap : getBitmapFromUri()
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.petInfoTb.setOnClickListener {
            finish()
        }
        val goodAt = intent.getStringExtra("goodAt")
        val jobName = intent.getStringExtra("jobName")
        val name = intent.getStringExtra("name")
        val practiceNo = intent.getStringExtra("practiceNo")
        val avatar = intent.getStringExtra("avatar")
        val id = intent.getStringExtra("id")
        tool.apply {
            Glide.with(context).load(avatar?.let { getUrl(it) }).into(vb.petInfoAvatar)
            vb.petInfoGoodAt.text = "擅长领域:$goodAt"
            vb.petInfoName.text = "名字:$name"
            vb.petInfoJobName.text = "职称:$jobName"
            vb.petInfoPracticeNo.text = "执业编号:$practiceNo"
        }
//        点击上传图片
        vb.petInfoUploading.setOnClickListener {
//            隐式跳转
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
//            指定只显示图片
            intent.type = "image/*"
            startActivityForResult(intent,fromAlbum)
        }
//        点击提交
        vb.petInfoBtn.setOnClickListener {
            val data = """
                {
                "doctorId": $id,
                "question": "${vb.petInfoInput.text}",
                "imageUrls":"${vb.petInfoImg}"
                }
            """.trimIndent()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            fromAlbum->{
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
//                        bitmap = getBitmapFromUri(uri)
//                        vb.petInfoImg.setImageBitmap(bitmap)
                    }
                }
            }
        }
    }
    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}