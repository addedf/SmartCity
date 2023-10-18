package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.R
import com.example.smartcity.bean.StopCarInfoBean
import com.example.smartcity.databinding.ActivityStopCarInfoBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import java.lang.Exception

class StopCarInfoActivity : AppCompatActivity() {
    lateinit var vb : ActivityStopCarInfoBinding
    val TAG = "StopCarInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityStopCarInfoBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.stopCarInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/park/lot/$id","GET",null,true) {
                val data = g.fromJson(it,StopCarInfoBean::class.java).data
                vb.stopCarInfoAddress.text = data.address
                vb.stopCarInfoAllPark.text = data.allPark
                vb.stopCarInfoOpen.text = when(data.open) {
                    "Y" -> "开放中"
                    else -> "未开放"
                }
                vb.stopCarInfoParkName.text = data.parkName
                vb.stopCarInfoPriceCaps.text = data.priceCaps
                vb.stopCarInfoRates.text = data.rates
                vb.stopCarInfoVacancy.text = data.vacancy
                try {
                    Glide.with(context).load(getUrl(data.imgUrl))
                        .error(getDrawable(R.drawable.chengshi))
                        .transform(CenterCrop(),RoundedCorners(5.dp))
                        .into(vb.stopCarInfoImgUrl)
                } catch (e:Exception) {
                    Log.e(TAG, "${e.message}")
                }
            }
        }
    }
}