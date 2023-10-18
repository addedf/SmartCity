package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RecBannerBean
import com.example.smartcity.bean.RecHotBean
import com.example.smartcity.databinding.ActivityRecSearchBinding
import com.example.smartcity.databinding.ItemRecHotBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class RecSearchActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRecSearchBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.recSearTb.setOnClickListener {
            finish()
        }
        loadBanner()
        loadHot()
    }

    @SuppressLint("SetTextI18n")
    private fun loadHot() {
        tool.apply {
            send("/prod-api/api/garbage-classification/garbage-classification/hot/list?pageNum=1&pageSize=10","GET",null,true) {
                val data = g.fromJson(it, RecHotBean::class.java).data.sortedByDescending { it.searchTimes }
                vb.recSeaHot.adapter = GenericAdapter(data.size,
                    { ItemRecHotBinding.inflate(layoutInflater)}) { binding,postion->
                    binding.recSeaHotSearchTime.text = "搜索次数:${data[postion].searchTimes}"
                    binding.recSeaHotKeyword.text = "关键词:${data[postion].keyword}"
                }
                vb.recSeaHot.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/garbage-classification/poster/list","GET",null,true) {
                val data = g.fromJson(it, RecBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.recSeaBanner, list)
            }
        }
    }
}