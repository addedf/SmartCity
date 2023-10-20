package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.GovListBean
import com.example.smartcity.bean.GovTypeBean
import com.example.smartcity.bean.GovernmentBannerBean
import com.example.smartcity.bean.HelpListBean
import com.example.smartcity.databinding.ActivityGarbageSortingBinding
import com.example.smartcity.databinding.ActivityGovernmentBinding
import com.example.smartcity.databinding.ItemGovListBinding
import com.example.smartcity.databinding.ItemGovServiceBinding
import okhttp3.internal.wait
import java.lang.Exception

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
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadList() {
        tool.apply {
            send(
                "/prod-api/api/gov-service-hotline/appeal/list?state=0&pageNum=1&pageSize=10",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(it, GovListBean::class.java).rows
                vb.govList.adapter = GenericAdapter(10,
                    { ItemGovListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itmeGovContent.text = data[position].content
                    binding.itmeGovTitle.text = data[position].title
                    binding.itmeGovUndertaker.text = "管理部门:" + data[position].undertaker
                    Log.e(TAG, "${data[position].detailResult}")
                    Glide.with(context).load(data[position].imgUrl?.let { it1 -> getUrl(it1) })
                        .error(getDrawable(R.drawable.chengshi))
                        .transform(CenterCrop(), RoundedCorners(5.dp))
                        .into(binding.itemGovImgUrl)
                    binding.root.setOnClickListener {
                        val intent = Intent(context, GovernmentInfoActivity::class.java)
                        intent.putExtra("createTime", data[position].createTime)
                        intent.putExtra("title", data[position].title)
                        intent.putExtra("content", data[position].content)
                        intent.putExtra("undertaker", data[position].undertaker)
                        intent.putExtra("appealCategoryName", data[position].appealCategoryName)
                        startActivity(intent)
                    }
                }
                vb.govList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadService() {
        tool.apply {
            send(
                "/prod-api/api/gov-service-hotline/appeal-category/list?pageNum=1&pageSize=10",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(it, GovTypeBean::class.java).rows.sortedBy { it.sort }
                vb.govService.adapter = GenericAdapter(data.size,
                    { ItemGovServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    if (position == 9) {
                        binding.itemGovName.text = "新建诉求"
                        Glide.with(context).load(R.drawable.ic_rec_clazz).into(binding.itemGovImg)
                        binding.root.setOnClickListener {
                            jump(SubmitLaimActivity::class.java)
                        }
                    } else {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemGovImg)
                        binding.itemGovName.text = data[position].name
                        binding.root.setOnClickListener {
                            val intent = Intent(context,GoverTypeListActivity::class.java)
                            intent.putExtra("id",data[position].id)
                            startActivity(intent)
                        }
                    }
//                    子项数据 先写定僵尸数据
                    binding.root.layoutParams = LinearLayout.LayoutParams(280, 210)
                }
//                横向滑动
                vb.govService.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/gov-service-hotline/ad-banner/list", "GET", null, true) {
                val data = g.fromJson(it, GovernmentBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.govBanner, list)
            }
        }
    }
}
