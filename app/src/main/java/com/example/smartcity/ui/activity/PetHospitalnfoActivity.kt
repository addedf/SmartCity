package com.example.smartcity.ui.activity


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.smartcity.bean.UploadBean
import com.example.smartcity.databinding.ActivityPetHospitalnfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.lang.Exception

class PetHospitalnfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetHospitalnfoBinding::inflate)
    val TAG = "PetHospitalnfoActivity"
    private val fromAlbum = 2

    //    延迟定义全局变量
//    private var selectedBitmap: Bitmap? = null
    private lateinit var imgUrl: String

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
//            带请求码跳转
            startActivityForResult(intent, fromAlbum)
        }
//        点击提交
        vb.petInfoBtn.setOnClickListener {
            val data = """
                {
                "doctorId": $id,
                "question": "${vb.petInfoInput.text}",
                "imageUrls":"$imgUrl"
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/pet-hospital/inquiry", "POST", req, true) { it ->
                    if (it.contains("操作成功")) {
                        Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        try {
                            Toast.makeText(
                                context,
                                JSONObject(it).getString("msg"),
                                Toast.LENGTH_SHORT
                            ).show()
                        } catch (_: Exception) {
                            Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            fromAlbum -> {
                if (resultCode == Activity.RESULT_OK && data != null) {
                    data.data?.let { uri ->
                        val bitmap = getBitmapFromUri(uri)
                        //                        把上传的图片展示再页面
                        vb.petInfoImg.setImageBitmap(bitmap)
                        tool.apply {
                            Log.e(TAG, "onActivityResult: ${uri.path}")
                            val file = File(uri.path)
                            val req = MultipartBody.Builder()
                                .setType(MultipartBody.FORM)
                                .addFormDataPart(
                                    "file",
                                    "image.jpg",
                                    RequestBody.create(
                                        "multipart/form-data".toMediaTypeOrNull(),
                                        file
                                    )
                                )
                                .build()
                            send("/prod-api/common/upload", "POST", req, true) {
                                if (it.contains("操作成功")) {
                                    val url = g.fromJson(it, UploadBean::class.java).url
                                    imgUrl = url
                                } else {
                                    try {
                                        Toast.makeText(
                                            context,
                                            JSONObject(it).getString("msg"),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } catch (_: Exception) {
                                        Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri) = contentResolver.openFileDescriptor(uri, "r")?.use {
        BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
    }
}