package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.TakeOutShopInfoBean
import com.example.smartcity.databinding.ActivityTakeOrderingFoodBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout

class TakeOrderingFoodActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOrderingFoodBinding::inflate)
    val TAG = "TakeOrderingFoodActivity"
    private val id : Int by lazy {
        intent.getIntExtra("id", 0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.takeOrderingTb.setOnClickListener {
            finish()
        }
        loadOrderingFood()
        loading()
        tool.apply {
            send("/prod-api/api/takeout/seller/$id","GET",null,false) {
                val data = g.fromJson(it,TakeOutShopInfoBean::class.java).data
                vb.takeOrderingText.text = data.name
                vb.takeOrderingName.text = data.name
                vb.takeOrderingAvgCost.text = "人均消费" + data.avgCost
                vb.takeOrderingScore.text = "评分：" +  data.score
                vb.takeOrderingSaleQuantity.text = "月销量：" + data.saleQuantity
                Glide.with(context).load(getUrl(data.imgUrl)).into(vb.takeOrderingImgUrl)
                Glide.with(context).load(getUrl(data.imgUrl)).into(vb.takeOrderingInfoImg)
                vb.takeOrderingInfoAddress.text = "地址" + data.address
                vb.takeOrderingInfoIntroduction.text = "主营业务:" + data.introduction
                val list = mutableListOf<String>()
                list.add("点餐")
                list.add("评价")
                list.add("店铺详情")
                for (i in list.indices) {
                    val newTab = vb.takeOrderingTab.newTab()
                    newTab.text = list[i]
                    vb.takeOrderingTab.addTab(newTab)
                }
                vb.tokeOutInfo.visibility = View.GONE
                vb.takeOutCommit.visibility = View.GONE
                vb.takeOutDiancan.visibility = View.VISIBLE
                vb.takeOrderingTab.addOnTabSelectedListener(object :
                    TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        vb.tokeOutInfo.visibility = View.GONE
                        vb.takeOutCommit.visibility = View.GONE
                        vb.takeOutDiancan.visibility = View.GONE
                        if (tab != null) {
                            when(tab.position) {
                                0 -> vb.takeOutDiancan.visibility = View.VISIBLE
                                1 -> vb.takeOutCommit.visibility = View.VISIBLE
                                2 -> vb.tokeOutInfo.visibility = View.VISIBLE
                            }
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

    private fun loading() {
        TODO("Not yet implemented")
    }

    private fun loadOrderingFood() {
        TODO("Not yet implemented")
    }


}