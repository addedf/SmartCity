package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.GovTypeBean
import com.example.smartcity.bean.GovernmentBannerBean
import com.example.smartcity.bean.HelpListBean
import com.example.smartcity.databinding.ActivityGarbageSortingBinding
import com.example.smartcity.databinding.ActivityGovernmentBinding
import com.example.smartcity.databinding.ItemGovServiceBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class GovernmentActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityGovernmentBinding::inflate)
    val TAG = "GovernmentActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.govTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                loadService()
                loadList()
            } else {
                tool.snackbar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/gov-service-hotline/appeal/my-list","GET",null,true) {
                Log.e(TAG, "$it")
            }
        }
    }

    private fun loadService() {
        tool.apply {
            send("/prod-api/api/gov-service-hotline/appeal-category/list?pageNum=1&pageSize=10","GET",null,true) {
                val data = g.fromJson(it,GovTypeBean::class.java).rows.sortedBy { it.sort }
                vb.govService.adapter = GenericAdapter(data.size,
                    { ItemGovServiceBinding.inflate(layoutInflater) }) { binding,position ->
//                    子项数据 先写定僵尸数据
                    binding.root.layoutParams = LinearLayout.LayoutParams(280,210)
                    binding.itemGovName.text = data[position].name
                    Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemGovImg)
                    binding.root.setOnClickListener {
                        val intent = Intent(context,GovernmentInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
//                横向滑动
                vb.govService.layoutManager = GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/gov-service-hotline/ad-banner/list","GET",null,true) {
                val data = g.fromJson(it,GovernmentBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.govBanner,list)
            }
        }
    }
}
