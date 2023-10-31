package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RegBean
import com.example.smartcity.databinding.ActivityRegAddlistBinding
import com.example.smartcity.databinding.ItemRegBinding
import com.google.android.material.tabs.TabLayout

class RegAddlistActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRegAddlistBinding::inflate)
    val TAG = "TAG"
    private val money: Float by lazy {
        intent.getFloatExtra("money", 0F)
    }
    private val categoryId: Int by lazy {
        intent.getIntExtra("categoryId", 0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        setSupportActionBar(vb.regAddListTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val categoryName = intent.getStringExtra("categoryName")
        intent.getFloatExtra("money", 0F)
        vb.regAddListText.text = categoryName
        loadTab()
    }

    private fun loadTab() {
        val list = mutableListOf<String>()
        list.add("普通挂号")
        list.add("专家挂号")
        for (i in list.indices) {
            val newTab = vb.regAddTab.newTab()
            newTab.text = list[i]
            vb.regAddTab.addTab(newTab)
        }
        vb.regAddTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    loadList(list[tab.position])
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        loadList(list[0])
    }

    private fun loadList(value: String) {
//        2普通1专家
        val type = when (value) {
            "普通挂号" -> 2
            else -> 1
        }
        tool.apply {
            send("/prod-api/api/hospital/reservation/list?type=$type", "GET", null, true) {
                val data = g.fromJson(it, RegBean::class.java).rows
                vb.regAddList.adapter = GenericAdapter(data.size,
                    { ItemRegBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemRegTitle.text = "预约人:" + data[position].patientName
                    binding.itemRegMoney.text = "预约类型:" + when (data[position].type) {
                        "1" -> "普通号"
                        else -> "专家号"
                    }
                    binding.itemRegType.text = "预约时间:" + data[position].reserveTime
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                    )
                }
                vb.regAddList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reg_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when (item.itemId) {
                R.id.reg_add_user -> {
//                    点击界面就把就诊费用传入添加界面
                    val intent = Intent(context, RegAddUserActivity::class.java)
                    intent.putExtra("money", money)
                    intent.putExtra("categoryId", categoryId)
                    startActivity(intent)
                }
            }
        }
        return true
    }
}