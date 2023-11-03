package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BusListBean
import com.example.smartcity.databinding.ActivitySmartBusBinding
import com.example.smartcity.databinding.ItemBusListBinding

class SmartBusActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivitySmartBusBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.busTb.setOnClickListener {
            finish()
        }
        loadList()
        vb.busAdd.setOnClickListener {
            jump(BusAddActivity::class.java)
        }
        vb.busMe.setOnClickListener {
            jump(BusMeActivity::class.java)
        }
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/bus/line/list","GET",null,false) {
                val data = g.fromJson(it,BusListBean::class.java).rows
                vb.busList.adapter = GenericAdapter(data.size,
                    { ItemBusListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemBusName.text = data[position].name
                    binding.itemBusPrice.text = "${data[position].price}元"
                    binding.itemBusEnd.text = "终点站:${data[position].end}"
                    binding.itemBusFirst.text = "起点站:${data[position].first}"
                    binding.itemBusStartTime.text = "第一班:${data[position].startTime}"
                    binding.itemBusEndTime.text = "末班车:${data[position].startTime}"
                    binding.root.setOnClickListener {
                        val intent = Intent(context,BusInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.busList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}