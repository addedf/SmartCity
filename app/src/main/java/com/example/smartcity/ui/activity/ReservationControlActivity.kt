package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.databinding.ActivityReservationControlBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class ReservationControlActivity : AppCompatActivity() {
    val TAG = "ReservationControlActivity"
    private val vb by viewBinding(ActivityReservationControlBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.resContTb.setOnClickListener {
            finish()
        }
        val Map =  getMapFromDB()
        val keys = Map.keys.map { it.first() }
        val arrayAdapter = ArrayAdapter(this, R.layout.item_sprnner,keys)
        vb.spinnerProvinces.adapter = arrayAdapter
        vb.spinnerProvinces.onItemSelectedListener = object : AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val city = keys[position]
                Toast.makeText(this@ReservationControlActivity,"You choose: $city ${Map[city]}",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun getMapFromDB(): HashMap<String, String> {
        val map = hashMapOf<String,String>()
        map["北京市"] = "京";
        map["上海市"] = "沪";
        map["天津市"] = "津";
        map["重庆市"] = "渝";
        map["黑龙江省"] = "黑";
        map["吉林省"] = "吉";
        map["辽宁省"] = "辽";
        map["河北省"] = "冀";
        map["甘肃省"] = "甘";
        map["青海省"] = "青";
        map["陕西省"] = "陕";
        map["河南省"] = "豫";
        map["山东省"] = "鲁";
        map["山西省"] = "晋";
        map["安徽省"] = "皖";
        map["湖北省"] = "鄂";
        map["湖南省"] = "湘";
        map["江苏省"] = "苏";
        map["四川省"] = "川";
        map["贵州省"] = "黔";
        map["云南省"] = "滇";
        map["广西省"] = "桂";
        map["浙江省"] = "浙";
        map["江西省"] = "赣 ";
        map["广东省"] = "粤";
        map["海南省"] = "琼";
        map["香港"] = "港";
        map["澳门"] = "澳";
        map["内蒙"] = "内 ";
        map["新疆"] = "新";
        map["广西"] = "桂";
        map["宁夏"] = "宁";
        map["西藏"] = "藏 ";
        return map
    }
}