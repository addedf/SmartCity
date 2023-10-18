package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.bean.YouthInnInfoBean
import com.example.smartcity.databinding.ActivityYiInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class YiInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityYiInfoBinding::inflate)
    val TAG = "YiInfoActivity"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.yiiTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id", 0)
        tool.apply {
            send("/prod-api/api/youth-inn/youth-inn/$id", "GET", null, true) {
                val data = g.fromJson(it, YouthInnInfoBean::class.java).data
                val imgList = data.imageUrls.split(",")
//                添加到集合
//                val imageSet = mutableSetOf<String>()
//                imageSet.addAll(imgList)
//                本来去完逗号就是列表了
//                val uniqueImages = imageSet.toList()
                setBanner(vb.yiiBanner,imgList)
                vb.yiiName.text = data.name
                vb.yiiAddress.text = data.address
                vb.yiiPhone.text = data.phone
                vb.yiiWorkTime.text = data.workTime
                vb.yiiBeds.text = "男(${data.bedsCountBoy})女(${data.bedsCountGirl})"
                vb.yiiIntroduce.text = data.introduce
                vb.yiiInternalFacilities.text = data.internalFacilities
                vb.yiiSurroundingFacilities.text = data.surroundingFacilities
                vb.yiiSpecialService.text = data.specialService
            }
        }
    }
}