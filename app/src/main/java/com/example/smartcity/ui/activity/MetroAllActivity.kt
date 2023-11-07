package com.example.smartcity.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.MetroAllBean
import com.example.smartcity.databinding.ActivityMetroAllBinding
import com.example.smartcity.databinding.ItemMetroAllBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class MetroAllActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMetroAllBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.metroAllTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/metro/line/list","GET",null,false) {
                val data = g.fromJson(it,MetroAllBean::class.java).data
                vb.metroAllList.adapter = GenericAdapter(data.size,
                    { ItemMetroAllBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemMetroAllText.text = data[position].lineName
//                    binding.itemMetroAllText.setTextColor(Color.rgb(64, 224, 208))
                }
                vb.metroAllList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}