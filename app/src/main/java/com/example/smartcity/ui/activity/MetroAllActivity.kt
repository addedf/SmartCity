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
import java.util.*

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
                    val random = Random()
                    val r = random.nextInt(256)
                    val g = random.nextInt(256)
                    val b = random.nextInt(256)
                    binding.name.text = data[position].lineName
                    binding.name.setTextColor(Color.rgb(r, g, b))
                    binding.view.setBackgroundColor(Color.rgb(r, g, b))
                }
                vb.metroAllList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}