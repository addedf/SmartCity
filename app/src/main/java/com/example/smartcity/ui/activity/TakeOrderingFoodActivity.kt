package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.TakeOrderingFoodListBean
import com.example.smartcity.bean.TakeOrderingIngBean
import com.example.smartcity.bean.TakeOutShopInfoBean
import com.example.smartcity.databinding.ActivityTakeOrderingFoodBinding
import com.example.smartcity.databinding.ItemEventsCommentBinding
import com.example.smartcity.databinding.ItemTakeOrderingFoodListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat

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
        tool.apply {
            send("/prod-api/api/takeout/comment/list?sellerId=$id","GET",null,false) {
                val data = g.fromJson(it, TakeOrderingIngBean::class.java).rows
                vb.takeOutCommitList.adapter = GenericAdapter(data.size,
                    { ItemEventsCommentBinding.inflate(layoutInflater) }) { binding, position ->
                    Glide.with(context).load(getUrl(data[position].avatar)).into(binding.itemEvenCommentAvatar)
                    binding.itemNewsCommentContent.text = data[position].content
                    binding.itemNewsCommentCreateTime.text = data[position].commentDate
                    binding.itemNewsCommentNickName.text = data[position].nickName
                }
                vb.takeOutCommitList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadOrderingFood() {
        var index = 0
        val decimalFormat = DecimalFormat("#.##")  // 处理小数点精度
        tool.apply {
            send("/prod-api/api/takeout/product/list?sellerId=4&categoryId=5","GET",null,false) {
                val data = g.fromJson(it, TakeOrderingFoodListBean::class.java).data
                vb.takeOutOrderingFood.adapter = GenericAdapter(data.size,
                    { ItemTakeOrderingFoodListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemTakeOrderingFoodName.text = data[position].name
                    binding.itemTakeOrderingFoodFavorRate.text = "好评率:" + data[position].favorRate
                    binding.itemTakeOrderingFoodPrice.text = "售价:" + data[position].price
                    binding.itemTakeOrderingFoodSaleQuantity.text = "月销售:" + data[position].saleQuantity
                    Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemTakeOrderingFoodImgUrl)
                    binding.itemTakeOrderingFoodAdd.setOnClickListener{
                        binding.itemTakeOrderingFoodInput.setText((++index).toString())
                        vb.takeOrderingPic.text = decimalFormat.format(data[position].price * index).toString()
                    }
                    binding.itemTakeOrderingFoodRemovr.setOnClickListener{
                        if (index>=1) {
                            binding.itemTakeOrderingFoodInput.setText((--index).toString())
                            vb.takeOrderingPic.text = decimalFormat.format(data[position].price * index).toString()
                        }
                    }
                    vb.takeOutOrderSub.setOnClickListener {
                        val intent = Intent(context,TakeOutClosActivity::class.java)
                        startActivity(intent)
                    }
                }
                vb.takeOutOrderingFood.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}