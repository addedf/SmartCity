package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.GovListBean
import com.example.smartcity.databinding.ActivityGoverTypeListBinding
import com.example.smartcity.databinding.ItemGovListBinding

class GoverTypeListActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityGoverTypeListBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.govTypeTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id", 0)
        tool.apply {
            send(
                "/prod-api/api/gov-service-hotline/appeal/list?appealCategoryId=${id}&pageNum=1&pageSize=10",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(it, GovListBean::class.java).rows
                vb.govTypeList.adapter = GenericAdapter(data.size,
                    { ItemGovListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.itmeGovContent.text = data[position].content
                    binding.itmeGovTitle.text = data[position].title
                    binding.itmeGovUndertaker.text = "管理部门:" + data[position].undertaker
                    Glide.with(context).load(data[position].imgUrl?.let { it1 -> getUrl(it1) })
                        .error(getDrawable(R.drawable.chengshi))
                        .transform(CenterCrop(), RoundedCorners(5.dp))
                        .into(binding.itemGovImgUrl)
                }
                vb.govTypeList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}