package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.CarManageBean
import com.example.smartcity.databinding.ActivityCarManageBinding
import com.example.smartcity.databinding.ItemCarManageBinding
import com.example.smartcity.dialog.AddCarInfoDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CarManageActivity : AppCompatActivity(),OnItemClickListener {
    private val vb by viewBinding(ActivityCarManageBinding::inflate)
    val TAG = "CarManageActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.carManageTb.setOnClickListener {
            finish()
        }
        setSupportActionBar(vb.carManageTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        loadCarList()
    }

    private fun loadCarList() {
        tool.apply {
            send("/prod-api/api/traffic/car/list","GET",null,true) {
                val data = g.fromJson(it,CarManageBean::class.java).rows
                vb.carManageList.adapter = GenericAdapter(data.size,
                    { ItemCarManageBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemCarMangerEngineNo.text = "发动机编号:" + data[position].engineNo
                    binding.itemCarMangerPlateNo.text = "车牌号:" + data[position].plateNo
                    binding.itemCarMangerType.text = "车辆类型" + data[position].type
                }
                vb.carManageList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.car_manage_add,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when(item.itemId) {
                R.id.car_manage_add -> {
                    AddCarInfoDialog(this@CarManageActivity,this@CarManageActivity).show()
                }
                R.id.carManage_tb -> finish()
            }
        }
        return true
    }

    override fun onItemClick(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onUserInfo(
        name: String,
        sex: String,
        userId: String?,
        phone: String,
        address: String
    ) {
        TODO("Not yet implemented")
    }


    override fun onCarInfo(engineNo: String, plateNo: String, type: String) {
        val data = """
            {
            "engineNo": "$engineNo",
            "plateNo": "$plateNo",
            "type": "$type"
            }
        """.trimIndent()
        val req = data.toRequestBody("application/json".toMediaTypeOrNull())
        tool.apply {
            send("/prod-api/api/traffic/car","POST",data,true) {
                if (it.contains("操作成功")){
                    loadCarList()
                } else {
                    Toast.makeText(context,"添加失败",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}