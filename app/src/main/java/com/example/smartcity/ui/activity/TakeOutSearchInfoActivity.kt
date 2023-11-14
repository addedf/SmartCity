package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.TakeOutSearchInfoBean
import com.example.smartcity.databinding.ActivityTakeOutSearchInfoBinding
import com.example.smartcity.databinding.ItemTakeOutSearchInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import java.lang.Exception
import kotlin.lazy as lazy1

class TakeOutSearchInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOutSearchInfoBinding::inflate)
    val TAG = "TakeOutSearchInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val shop = intent.getStringExtra("query")
        vb.takeOutSeanchInfoText.text = shop
        loadShop(shop)
    }

    private fun loadShop(shop: String?) {
        tool.apply {
            send("/prod-api/api/takeout/search?keyword=${shop}&pageNum=1&pageSize=1","GET",null,false) {
                val data = g.fromJson(it, TakeOutSearchInfoBean::class.java).rows
                vb.takeOutSearchInfoList.adapter = GenericAdapter(data.size,
                    { ItemTakeOutSearchInfoBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemTakeHomeName.text = data[position].name
                    binding.itemTakeHomeDeliveryTime.text = "配送时间:${data[position].deliveryTime}分钟"
                    binding.itemTakeHomeScore.text = "评分:${data[position].score} | 月售:${data[position].avgCost}"
                    binding.itemTakeHomeDistance.text = "距离:${data[position].distance}"
                    binding.itemTakePrice.text = "${data[position].productList[0].price}元"
                    binding.itemTakeSonname.text = data[position].productList[0].name
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemTakeHomeImgUrl)
                        Glide.with(context).load(getUrl(data[position].productList[0].imgUrl)).into(binding.itemTakeOutImgUrl)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadShop: ${e.message}")
                    }
                }
                vb.takeOutSearchInfoList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}