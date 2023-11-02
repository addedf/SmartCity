package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcity.R
import com.example.smartcity.bean.BusInfoBean
import com.example.smartcity.databinding.ActivityBusInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class BusInfoActivity : AppCompatActivity() {
    val TAG = "BusInfoActivity"
    private val vb by viewBinding(ActivityBusInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.busTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/bus/line/$id","GET",null,false) {
                val data = g.fromJson(it,BusInfoBean::class.java).data
                vb.busInfoName.text = data.name
                vb.busInfoEndTime.text = data.endTime
                vb.busInfoStartTime.text = data.startTime
                vb.busInfoFirst.text = data.first
                vb.busInfoEnd.text = data.end
                vb.busInfoPrice.text = data.price.toString()
                vb.busInfoMileage.text = data.mileage
            }
        }
    }
}