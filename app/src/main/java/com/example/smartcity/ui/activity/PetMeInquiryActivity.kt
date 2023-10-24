package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.PetInquiryBean
import com.example.smartcity.databinding.ActivityPetInquiryListBinding
import com.example.smartcity.databinding.ActivityPetMeInquiryBinding
import com.example.smartcity.databinding.ItemPemMeListBinding

class PetMeInquiryActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityPetMeInquiryBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.petMeTb.setOnClickListener {
            finish()
        }
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/pet-hospital/inquiry/my-list","GET",null,true) {
                val data = g.fromJson(it,PetInquiryBean::class.java).rows
                vb.petMeList.adapter = GenericAdapter(data.size,
                    { ItemPemMeListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemPetQuestion.text = data[position].question
                    binding.itemPetCreateTime.text = data[position].createTime
                    Glide.with(context).load(getUrl(data[position].imageUrls)).into(binding.itemPetImg)
//                    点击进行追问
                    binding.root.setOnClickListener {
                        jump(QuestionCloselyActivity::class.java)
                    }
                }
                vb.petMeList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}