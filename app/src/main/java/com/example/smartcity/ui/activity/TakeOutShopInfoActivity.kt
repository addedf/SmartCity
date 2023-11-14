package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.TakeOutShopInfoBean
import com.example.smartcity.databinding.ActivityTakeOutShopInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class TakeOutShopInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOutShopInfoBinding::inflate)
    val TAG = "TakeOutShopInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.takeOutTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/takeout/seller/$id","GET",null,false){
                val data = g.fromJson(it,TakeOutShopInfoBean::class.java).data
                vb.takeShopAddress.text = data.address
                vb.takeShopAvgCost.text = data.avgCost.toString()
                vb.takeShopCreateTime.text = data.createTime
                vb.takeShopDeliveryTime.text = data.deliveryTime.toString()
                vb.takeShopDistance.text = data.deliveryTime.toString()
                vb.takeShopIntroduction.text = data.introduction
                vb.takeShopName.text = data.name
                vb.takeShopScore.text = data.score.toString()
                Glide.with(context).load(getUrl(data.imgUrl)).into(vb.takeShopImg)
            }
        }
    }
}