package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity.bean.WelfareInfoBean
import com.example.smartcity.databinding.ActivityWelfareInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class WelfareInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityWelfareInfoBinding::inflate)
    val TAG = "WelfareInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.weInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        val name = intent.getStringExtra("name")
        val imgUrl = intent.getStringExtra("imgUrl")
        tool.apply {
            send("/prod-api/api/public-welfare/donate-record/list?activityId=${id}&pageNum=1&pageSize=1","GET",null,true) {
                val data = g.fromJson(it, WelfareInfoBean::class.java).rows
                Log.e(TAG, "$it")
                vb.weInfoCreateTime.text = data[0].createTime
                vb.weInfoDonateMoney.text = data[0].donateMoney.toString() + "元"
                vb.weInfoName.text = data[0].userName
                vb.weInfoType.text = data[0].activityName
                Glide.with(context).load(imgUrl?.let { it1 -> getUrl(it1) })
                    .into(vb.weInfoImg)
            }
        }
    }
}