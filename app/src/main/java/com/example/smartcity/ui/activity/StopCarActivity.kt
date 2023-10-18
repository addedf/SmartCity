package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.StopCarBean
import com.example.smartcity.databinding.ActivityStopCarBinding
import com.example.smartcity.databinding.ItemStopCarListBinding
import java.lang.Exception

class StopCarActivity : AppCompatActivity() {
    lateinit var vb: ActivityStopCarBinding
    val TAG = "StopCarActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityStopCarBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.stopCarTb.setOnClickListener {
            finish()
        }
        setSupportActionBar(vb.stopCarTb)
//        禁止显示菜单的应用名字
        supportActionBar?.setDisplayShowTitleEnabled(false)
//        验证是否登录
        tool.checkToken {
            if (it) {
//                停车场列表
                loadStopCarParkingLotList()
            } else {
                Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show()
            }
        }

    }
//    添加菜单栏内容
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.stop_car,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when(item.itemId) {
                R.id.stop_car_Records -> jump(StopCarRecordsActivity::class.java)
                R.id.stopCar_tb -> finish()
            }
        }
        return true
    }

    private fun loadStopCarParkingLotList() {
        tool.apply {
            send("/prod-api/api/park/lot/list", "GET", null, false) {
                val data = g.fromJson(it, StopCarBean::class.java).rows
                val adapter = GenericAdapter(data.size,
                    { ItemStopCarListBinding.inflate(layoutInflater) }) { binding, position ->
//                    点击进入停车场详情界面
                    binding.root.setOnClickListener {
                        val intent = Intent(context,StopCarInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.itemStopCarAddress.text = data[position].address
                    binding.itemStopCarDistance.text = data[position].distance
                    binding.itemStopCarParkName.text = data[position].parkName
                    binding.itemStopCarRates.text = data[position].rates
                    binding.itemStopCarVacancy.text = data[position].vacancy
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl))
//                                图片圆角
                            .transform(CenterCrop(), RoundedCorners(5.dp))
                            .error(getDrawable(R.drawable.chengshi))
                            .into(binding.itemStopCarImgUrl)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.stopCarParkingLotList.adapter = adapter
                vb.stopCarParkingLotList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}