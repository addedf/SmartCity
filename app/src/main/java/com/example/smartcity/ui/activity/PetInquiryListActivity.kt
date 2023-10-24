package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.PetInqioryBean
import com.example.smartcity.bean.PetInquiryBean
import com.example.smartcity.databinding.ActivityPetInquiryListBinding
import com.example.smartcity.databinding.ItemPetInquiryListBinding
import java.lang.Exception

class PetInquiryListActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetInquiryListBinding::inflate)
    val TAG = "PetInquiryListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.petInqTb.setOnClickListener {
            finish()
        }
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/pet-hospital/inquiry/list?pageNum=10&pageSize=10","GET",null,true) {
                val data = g.fromJson(it, PetInqioryBean::class.java).rows
                vb.petInqList.adapter = GenericAdapter(data.size,
                    { ItemPetInquiryListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemPetQuestion.text = data[position].question
                    binding.temPetCreateTime.text = data[position].createTime
                    try {
                        binding.temPetName.text = data[position].doctor.name
                        Glide.with(context).load(getUrl(data[position].imageUrls))
                            .error(getDrawable(R.drawable.chengshi))
                            .transform(CenterCrop(),RoundedCorners(5.dp))
                            .into(binding.itemPetImageUrls)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
//                    点金进入案例详情 传递值
                    binding.root.setOnClickListener {
                        val intent = Intent(context,PetInquiryInfoActivity::class.java)
                        intent.putExtra("name",data[position].doctor.name)
                        intent.putExtra("avatar",data[position].doctor.avatar)
                        intent.putExtra("goodAt",data[position].doctor.goodAt)
                        intent.putExtra("jobName",data[position].doctor.jobName)
                        intent.putExtra("practiceNo",data[position].doctor.practiceNo)
                        intent.putExtra("question",data[position].question)
                        intent.putExtra("imageUrls",data[position].imageUrls)
                        startActivity(intent)
                    }
                }
                vb.petInqList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}