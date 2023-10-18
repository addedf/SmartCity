package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.HouseBean
import com.example.smartcity.databinding.ActivityHouseBinding
import com.example.smartcity.databinding.ItemListHouseBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class HouseActivity : AppCompatActivity() {
    lateinit var vb: ActivityHouseBinding
    val TAG = "HouseActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityHouseBinding.inflate(layoutInflater)
        loadTab()
//        默认加载列表
        loadHouse("二手")
        loadSearch()
        setContentView(vb.root)
        vb.houseTb.setOnClickListener {
            finish()
        }

    }
    //    TODO("模糊搜索功能未完成")
    private fun loadSearch() {
    }
    //    加载房子类型
    private fun loadTab() {
        tool.apply {
            send("/prod-api/api/house/housing/list", "GET", null, false) {
                val data = g.fromJson(it, HouseBean::class.java).rows
//                HashSet 存放字符串的哈希集合 去除重复值保留房子类型遍历到tab
                val houseTypeSet = HashSet<String>()
                for (tab in data) {
                    val houseType = tab.houseType
//                    判断房子类型是否存在 在去除不在添加
                    if (houseTypeSet.add(houseType)) {
                        val newTab = vb.houseTab.newTab()
                        newTab.text = houseType
                        vb.houseTab.addTab(newTab)
                    }
                }

//                监测tab点击
                vb.houseTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
//                        根据序号判断房子类型  在把房子类型传入加载列表方法
                        val selectedHouseType =
                            tab?.position?.let { it1 -> vb.houseTab.getTabAt(it1)?.text.toString() }
                        if (selectedHouseType != null) {
                            loadHouse(selectedHouseType)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                })
            }
        }
    }

    //    加载房子列表
    private fun loadHouse(selectedHouseType: String) {
        tool.apply {
            send("/prod-api/api/house/housing/list", "GET", null, false) {
                val data = g.fromJson(it, HouseBean::class.java).rows
                // 使用 filter 函数过滤数据，只保留需要的房子类型
                val filteredHouseData = data.filter { it.houseType == selectedHouseType }
                val adapter = GenericAdapter(filteredHouseData.size,
                    { ItemListHouseBinding.inflate(layoutInflater) }) { binding, position ->
                    val house = filteredHouseData[position]
                    binding.itemListSourceName.text = house.sourceName
                    binding.houseItemDescription.text = house.description
                    binding.houseItemPrice.text = house.price
//                    点击跳转详情
                    binding.root.setOnClickListener {
                        val intent = Intent(context,HouseDetailsActivity::class.java)
                        intent.putExtra("id",filteredHouseData[position].id)
                        startActivity(intent)
                    }
//                    有的房子没有图片需要用try保住才能正常加载 下次直接在实体类加问号提前进行空指针处理
                    try {
//                        没有图片加载本地图片 item也要加本地图片
                        Glide.with(context).load(getUrl(house.pic))
                            .error(getDrawable(R.drawable.chengshi))
                            .transform(CenterCrop(), RoundedCorners(5.dp))
                            .into(binding.houseItemPic)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }

                }
                vb.houseList.adapter = adapter
                vb.houseList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

}