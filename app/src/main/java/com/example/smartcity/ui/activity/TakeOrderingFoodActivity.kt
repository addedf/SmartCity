package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.FoodTypeBean
import com.example.smartcity.bean.TakeOrderingFoodListBean
import com.example.smartcity.bean.TakeOrderingIngBean
import com.example.smartcity.bean.TakeOutShopInfoBean
import com.example.smartcity.databinding.ActivityTakeOrderingFoodBinding
import com.example.smartcity.databinding.ItemEventsCommentBinding
import com.example.smartcity.databinding.ItemTakeOrderingFoodListBinding
import com.example.smartcity.databinding.ItemTokaeTextBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout
import java.text.DecimalFormat

class TakeOrderingFoodActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOrderingFoodBinding::inflate)
    val TAG = "TakeOrderingFoodActivity"
    var TotalPrice = 0.0
    lateinit var datas : List<List<TakeOrderingFoodListBean.DataBean>>
    private val id : Int by lazy {
        intent.getIntExtra("id", 0)
    }
    private val decimalFormat = DecimalFormat("#.##")  // 处理小数点精度
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.takeOrderingTb.setOnClickListener {
            finish()
        }
//        loadOrderingFood()
        loading()
        loadFoodList(id)
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

    private fun loadFoodList(id: Int) {
        tool.apply {
            send("/prod-api/api/takeout/category/list?sellerId=$id","GET",null,false) {
                val data = g.fromJson(it, FoodTypeBean::class.java).data
                loadFood(id,data[0].id)
                vb.orderTakeoutTextDishes.adapter = GenericAdapter(data.size,
                    { ItemTokaeTextBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.mItemTakeDishes.text = data[position].name
                    binding.root.setOnClickListener {
                        loadFood(id,data[position].id)
                    }
                }
                vb.orderTakeoutTextDishes.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadFood(id: Int, categoryId: Int?) {
        tool.apply {
            send("/prod-api/api/takeout/product/list?sellerId=$id&categoryId=$categoryId","GET",null,false) {
                val data = g.fromJson(it, TakeOrderingFoodListBean::class.java)
                vb.takeOutOrderingFood.adapter = GenericAdapter(data.data.size,
                    { ItemTakeOrderingFoodListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemTakeOrderingFoodName.text = data.data[position].name
                    binding.itemTakeOrderingFoodFavorRate.text = "好评率:" + data.data[position].favorRate
                    binding.itemTakeOrderingFoodPrice.text = "售价:" + data.data[position].price
                    binding.itemTakeOrderingFoodSaleQuantity.text = "月销售:" + data.data[position].saleQuantity
                    Glide.with(context).load(getUrl(data.data[position].imgUrl)).into(binding.itemTakeOrderingFoodImgUrl)
                    binding.itemTakeOrderingFoodAdd.setOnClickListener{
                        binding.itemTakeOrderingFoodInput.setText((++data.data[position].index).toString())
//                        data.data[position].total = data.data[position].price * data.data[position].index
                        TotalPrice = data.data[position].price * data.data[position].index
                        vb.takeOrderingPic.text = TotalPrice.toString()
                        Log.e(TAG, "loadFood: $TotalPrice")
                    }
                    binding.itemTakeOrderingFoodRemovr.setOnClickListener{
                        if (data.data[position].index>=1) {
                            binding.itemTakeOrderingFoodInput.setText((--data.data[position].index).toString())
//                            data.data[position].total = data.data[position].price * data.data[position].index
                            TotalPrice = data.data[position].price * data.data[position].index
                            vb.takeOrderingPic.text = TotalPrice.toString()
                        } else if (data.data[position].index == 0) {
                            vb.takeOrderingPic.text = decimalFormat.format(0 * data.data[position].index).toString()
                            vb.takeOrderingPic.text = TotalPrice.toString()
                        }
                    }
                    vb.takeOutOrderSub.setOnClickListener {
                        var isDiancan = false
                        if (TotalPrice>0.0) {
                            isDiancan = true
                        }
                        if (isDiancan) {
                            val intent = Intent(context,TakeOutClosActivity::class.java)
                            intent.putExtra("data", g.toJson(data))
                            startActivity(intent)
                        } else {
                            Toast.makeText(context,"未点餐",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                vb.takeOutOrderingFood.layoutManager = LinearLayoutManager(context)
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

//    private fun loadOrderingFood() {
////        思路通过给实体类添加自定义字段来记录单个菜品的操作和特有属性
//        tool.apply {
//            send("/prod-api/api/takeout/product/list?sellerId=4&categoryId=5","GET",null,false) {
//                val data = g.fromJson(it, TakeOrderingFoodListBean::class.java)
//                vb.takeOutOrderingFood.adapter = GenericAdapter(data.data.size,
//                    { ItemTakeOrderingFoodListBinding.inflate(layoutInflater) }) { binding,position->
//                    binding.itemTakeOrderingFoodName.text = data.data[position].name
//                    binding.itemTakeOrderingFoodFavorRate.text = "好评率:" + data.data[position].favorRate
//                    binding.itemTakeOrderingFoodPrice.text = "售价:" + data.data[position].price
//                    binding.itemTakeOrderingFoodSaleQuantity.text = "月销售:" + data.data[position].saleQuantity
//                    Glide.with(context).load(getUrl(data.data[position].imgUrl)).into(binding.itemTakeOrderingFoodImgUrl)
//                    binding.itemTakeOrderingFoodAdd.setOnClickListener{
//                        binding.itemTakeOrderingFoodInput.setText((++data.data[position].index).toString())
//                        data.data[position].total = data.data[position].price * data.data[position].index
//                        sumTotalPrice(data.data, vb.takeOrderingPic)
//                    }
//                    binding.itemTakeOrderingFoodRemovr.setOnClickListener{
//                        if (data.data[position].index>=1) {
//                            binding.itemTakeOrderingFoodInput.setText((--data.data[position].index).toString())
//                            data.data[position].total = data.data[position].price * data.data[position].index
//                            sumTotalPrice(data.data, vb.takeOrderingPic)
//                        } else if (data.data[position].index == 0) {
//                            vb.takeOrderingPic.text = decimalFormat.format(0 * data.data[position].index).toString()
//                            sumTotalPrice(data.data, vb.takeOrderingPic)
//                        }
//                    }
//                    vb.takeOutOrderSub.setOnClickListener {
//                        var isDiancan = false
//                        for (i in data.data.indices) {
//                            if (data.data[i].total>=1) {
//                                isDiancan = true
//                            }
//                        }
//                        if (isDiancan) {
//                            val intent = Intent(context,TakeOutClosActivity::class.java)
//                            intent.putExtra("data", g.toJson(data))
//                            startActivity(intent)
//                        } else {
//                            Toast.makeText(context,"未点餐",Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//                vb.takeOutOrderingFood.layoutManager = LinearLayoutManager(context)
//            }
//        }
//    }
//    private fun sumTotalPrice(data: MutableList<TakeOrderingFoodListBean.DataBean>, takeOrderingPic: TextView) {
//        var sumTotalPrice = 0.0
//        for (item in data) {
//            sumTotalPrice += item.total
//        }
//        takeOrderingPic.text = decimalFormat.format(sumTotalPrice).toString()
//    }
}