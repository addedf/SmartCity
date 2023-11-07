package com.example.smartcity.ui.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.MetroInfo
import com.example.smartcity.databinding.ActivityMetroListBinding
import com.example.smartcity.databinding.ItemMetroInfoListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class MetroInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMetroListBinding::inflate)
    val TAG = "MetroInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.metroInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/metro/line/$id","GET",null,false) {
                val data = g.fromJson(it,MetroInfo::class.java).data
                vb.metroInfoText.text = data.name
                vb.metroInfoEnd.text = "终点:" + data.end
                vb.metroInfoFirst.text = "起点:" + data.first
                vb.metroInfoStartTime.text = "首:" + data.startTime
                vb.metroInfoEndTime.text = "末:" + data.endTime
                vb.metroInfoRunStationsName.text = data.runStationsName
                vb.metroInfoKm.text = "${data.km}km/${data.stationsNumber}站"
                vb.metroInfoList.adapter = GenericAdapter(data.metroStepList.size,
                    { ItemMetroInfoListBinding.inflate(layoutInflater) }) { binding,position->
                    if (data.runStationsName.equals(data.metroStepList[position].name)) {
                        binding.itemMetroInfoListName.setTextColor(Color.rgb(64, 224, 208))
                    }
                    binding.itemMetroInfoListName.text = data.metroStepList[position].name
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        500,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                }
                vb.metroInfoList.layoutManager =
                    GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            }
        }
    }
}