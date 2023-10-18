package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.StopCarRecordsBean
import com.example.smartcity.databinding.ActivityStopCarRecordsBinding
import com.example.smartcity.databinding.ItemStopCarRecordsBinding
import com.example.smartcity.g
import com.example.smartcity.tool

class StopCarRecordsActivity : AppCompatActivity() {
    lateinit var vb : ActivityStopCarRecordsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityStopCarRecordsBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.stopCarRecordsTb.setOnClickListener {
            finish()
        }
        tool.apply {
            tool.send("/prod-api/api/park/lot/record/list","GET",null,false) {
                val rows = g.fromJson(it,StopCarRecordsBean::class.java).rows
                val adapter = GenericAdapter(rows.size,
                { ItemStopCarRecordsBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemStopCarRecordsParkName.text = rows[position].parkName
                    binding.itemStopCarRecordsEntryTime.text = "入场" + rows[position].entryTime
                    binding.itemStopCarRecordsOutTime.text = "入场" + rows[position].outTime
                    binding.itemStopCarRecordsPlateNumber.text = "车牌号:" + rows[position].plateNumber
                    binding.itemStopCarRecordsMonetary.text = rows[position].monetary + "元"
                }
                vb.stopCarRecordsList.adapter = adapter
                vb.stopCarRecordsList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}