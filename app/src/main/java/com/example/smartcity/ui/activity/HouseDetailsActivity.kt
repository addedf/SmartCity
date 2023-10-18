package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.R
import com.example.smartcity.bean.HouseDetailsBean
import com.example.smartcity.databinding.ActivityHouseDetailsBinding
import com.example.smartcity.dp
import com.example.smartcity.tool
import com.google.gson.Gson

class HouseDetailsActivity : AppCompatActivity() {
    lateinit var vb: ActivityHouseDetailsBinding
    val TAG = "HouseDetailsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityHouseDetailsBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.detTb.setOnClickListener {
            finish()
        }
//        接收传递过来的id
        val id = intent.getIntExtra("id", 0)
        tool.apply {
            send("/prod-api/api/house/housing/$id", "GET", null, false) {
//               后端字段有部分数据确实避免空指针
                val data = Gson().fromJson(it, HouseDetailsBean::class.java).data
                vb.detSourceName.text = data.sourceName
                vb.detAddress.text = data.address
                vb.detAreaSize.text = data.areaSize.toString()
                vb.detHouseType.text = data.houseType
                vb.detDescription.text = data.description
                vb.detTel.text = data.tel
                vb.detPrice.text = data.price
                Glide.with(context).load(data.pic?.let { it1 -> getUrl(it1) })
                    .error(getDrawable(R.drawable.chengshi))
                    .transform(
                        CenterCrop(), RoundedCorners(5.dp)
                    )
                    .into(vb.detPic)
            }
        }
    }
}