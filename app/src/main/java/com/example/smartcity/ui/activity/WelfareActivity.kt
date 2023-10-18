package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.WelfareBannerBean
import com.example.smartcity.bean.WelfareRecommendBean
import com.example.smartcity.bean.WelfareTypeBean
import com.example.smartcity.databinding.ActivityWelfareBinding
import com.example.smartcity.databinding.ItemWelfareListBinding
import com.example.smartcity.databinding.ItemWelfareTypeBinding
import java.lang.Exception

class WelfareActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityWelfareBinding::inflate)
    val TAG = "WelfareActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.waTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
//                工艺分类
                loadType()
                loadRecommend()
            } else {
                tool.snackbar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadRecommend() {
        tool.apply {
            send("/prod-api/api/public-welfare/public-welfare-activity/recommend-list","GET",null,true) {
                val data = g.fromJson(it, WelfareRecommendBean::class.java).rows
                vb.waRecommend.adapter = GenericAdapter(data.size,
                    { ItemWelfareListBinding.inflate(layoutInflater) }) {binding,position ->
                    binding.waItemAuthor.text = data[position].author
                    binding.waItemName.text = data[position].name
                    binding.waItemMoney.text = "已筹善款:${data[position].moneyTotal}/${data[position].moneyNow}(元)"
                    binding.waItemDonateCount.text = "${data[position].donateCount}人已捐"
                    Glide.with(context).load(getUrl(data[position].imgUrl))
                        .transform(CenterCrop(),RoundedCorners(5.dp))
                        .into(binding.ItemWaCover)
                    binding.root.layoutParams = RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
//                    点击跳转详情页面
                    binding.root.setOnClickListener {
                        val intent = Intent(context,WelfareInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        intent.putExtra("name",data[position].type.name)
                        intent.putExtra("imgUrl",data[position].imgUrl)
                        startActivity(intent)
                    }
                }
                vb.waRecommend.layoutManager = object: LinearLayoutManager(context){
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadType() {
        tool.apply {
            send("/prod-api/api/public-welfare/public-welfare-type/list","GET",null,true) {
                val data = g.fromJson(it,WelfareTypeBean::class.java).data
                vb.waType.adapter = GenericAdapter(8,
                    { ItemWelfareTypeBinding.inflate(layoutInflater) }) { binding,postiont ->
                    binding.waTypeName.text = data[postiont].name
                    try {
                        Glide.with(context).load(getUrl(data[postiont].imgUrl))
                            .into(binding.waTypeIcon)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.waType.layoutManager = GridLayoutManager(context,4)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/public-welfare/ad-banner/list","GET",null,true) {
                val data = g.fromJson(it,WelfareBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.waBanner,list)
            }
        }
    }
}