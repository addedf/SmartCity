package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.bean.DABean
import com.example.smartcity.databinding.ActivityDataAnalysisBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class DataAnalysisActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityDataAnalysisBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.dataAnaTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/press/press/list?pageNum=1&pageSize=5","GET",null,false){
                val data = g.fromJson(it, DABean::class.java).rows
                val strList = mutableListOf<String>()
                val barList = mutableListOf<BarEntry>()
//                遍历1-5
                for(i in 0 until 5){
                    strList.add(data[i].title)
//                    设置横纵坐标
                    barList.add(BarEntry(i.toFloat(), data[i].likeNum.toFloat()))
                }
                val dataSet = LineDataSet(barList.toList(),"新闻点赞数据分析")   // title
                dataSet.colors = ColorTemplate.VORDIPLOM_COLORS.toList()   // 折线颜色
                val lineData = LineData(dataSet)
                vb.lineChart.data=lineData
                vb.lineChart.invalidate()
            }
        }
    }
}