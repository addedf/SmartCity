package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RecTypeBean
import com.example.smartcity.databinding.ActivityRecClazzBinding
import com.example.smartcity.databinding.ItemRecTypeBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class RecClazzActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRecClazzBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.recClazzTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/garbage-classification/garbage-classification/list","GET",null,true) {
                val data = g.fromJson(it,RecTypeBean::class.java).rows
                vb.recClazzList.adapter = GenericAdapter(data.size,{ ItemRecTypeBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemRecGuide.text = data[position].guide
                    binding.itemRecIntroduce.text = data[position].introduce
                    binding.itemRecName.text = data[position].name
                    Glide.with(context).load(getUrl(data[position].imgUrl))
                        .into(binding.itemRecImgUrl)
                }
                vb.recClazzList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}