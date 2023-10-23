package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.PetDoctorLIstBean
import com.example.smartcity.bean.PetTypeBean
import com.example.smartcity.databinding.*
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import java.lang.Exception

class PetHospitalActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetHospitalBinding::inflate)
    val TAG = "PetHospitalActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.petTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadType()
                loadDoctorList()
//                问诊列表
                vb.petInquiryList.setOnClickListener {
                    startActivity(Intent(this,PetMeInquiryActivity::class.java))
                }
//                问诊案例
                vb.petMeInquiry.setOnClickListener {
                    startActivity(Intent(this,PetInquiryListActivity::class.java))
                }
            } else {
                tool.snackbar(vb.root, "未登录", "去登录") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadDoctorList() {
        tool.apply {
            send("/prod-api/api/pet-hospital/pet-doctor/list","GET",null,true) {
                val data = g.fromJson(it,PetDoctorLIstBean::class.java).rows
                vb.petDoctorList.adapter = GenericAdapter(data.size,
                    { ItemDoctorListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.itemDoctorGoodAt.text = "擅长领域:" + data[position].goodAt
                    binding.itemDoctorJobName.text = "职称:" + data[position].jobName
                    binding.itemDoctorName.text = "名字:" + data[position].name
                    binding.itemDoctorPracticeNo.text = "执业编号:" + data[position].practiceNo
                    try {
                        Glide.with(context).load(getUrl(data[position].avatar)).into(binding.itemDoctorAvatar)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadDoctorList:${e.message}")
                    }
//                    点击跳转问诊详情界面
                    binding.root.setOnClickListener {
                        val intent = Intent(context,PetHospitalnfoActivity::class.java)
                        intent.putExtra("goodAt",data[position].goodAt)
                        intent.putExtra("jobName",data[position].jobName)
                        intent.putExtra("name",data[position].name)
                        intent.putExtra("practiceNo",data[position].practiceNo)
                        intent.putExtra("avatar",data[position].avatar)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.petDoctorList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadType() {
        tool.apply {
            send("/prod-api/api/pet-hospital/pet-type/list", "GET", null, true) {
                val data = g.fromJson(it, PetTypeBean::class.java).data
                vb.petType.adapter = GenericAdapter(data.size,
                    { ItemServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemServiceName.text = data[position].name
                    Glide.with(context).load(getUrl(data[position].imgUrl))
                        .into(binding.itemServiceIcon)
                }
                vb.petType.layoutManager = GridLayoutManager(context, 5)
            }
        }
    }
}