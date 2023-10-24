package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.CallDoctorBean
import com.example.smartcity.databinding.ActivityCallDoctorBinding
import com.example.smartcity.databinding.ItemDoctorListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import java.lang.Exception

class CallDoctorActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityCallDoctorBinding::inflate)
    val TAG = "CallDoctorActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.callTb.setOnClickListener {
            finish()
        }
        val typeId = intent.getIntExtra("typeId",0)
        Log.e(TAG, "$typeId")
        loadList(typeId)
    }

    private fun loadList(typeId: Int) {
        tool.apply {
            send(
                "/prod-api/api/pet-hospital/pet-doctor/list?typeId=${typeId}&pageSize=10&pageNum=1",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(it, CallDoctorBean::class.java).rows
                vb.callList.adapter = GenericAdapter(data.size,
                    { ItemDoctorListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.itemDoctorGoodAt.text = "擅长领域:" + data[position].goodAt
                    binding.itemDoctorJobName.text = "职称:" + data[position].jobName
                    binding.itemDoctorName.text = "名字:" + data[position].name
                    binding.itemDoctorPracticeNo.text = "执业编号:" + data[position].practiceNo
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
                    try {
                        Glide.with(context).load(getUrl(data[position].avatar))
                            .into(binding.itemDoctorAvatar)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.callList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}