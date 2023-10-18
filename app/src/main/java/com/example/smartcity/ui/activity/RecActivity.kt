package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RecBannerBean
import com.example.smartcity.bean.RecNewsClazzBean
import com.example.smartcity.bean.RecNewsListBean
import com.example.smartcity.databinding.ActivityRecBinding
import com.example.smartcity.databinding.ItemRecNewsListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class RecActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRecBinding::inflate)
    val TAG = "RecActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.recTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                loadNewsClazz()
                vb.recSearch.setOnClickListener {
                    startActivity(Intent(this, RecSearchActivity::class.java))
                }
                vb.recClazz.setOnClickListener {
                    startActivity(Intent(this, RecClazzActivity::class.java))
                }
            } else {
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadNewsClazz() {
        tool.apply {
            send("/prod-api/api/garbage-classification/news-type/list", "GET", null, true) {
                val data = g.fromJson(it, RecNewsClazzBean::class.java).rows
                for (row in data) {
                    vb.recNewsClazz.addTab(vb.recNewsClazz.newTab().setText(row.name))
                }
                vb.recNewsClazz.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab != null) {
                            loadList(data[tab.position].id)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }
                })
                loadList(data[0].id)
            }
        }
    }

    private fun loadList(id: Int) {
        tool.apply {
            send(
                "/prod-api/api/garbage-classification/news/list?type=$id",
                "GET",
                null,
                true
            ) {
                val data =
                    g.fromJson(it, RecNewsListBean::class.java).rows.sortedBy { it.createTime }
                vb.recNewsList.adapter = GenericAdapter(data.size,
                    { ItemRecNewsListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.itemRecNewsDate.text = "创建时间:" + data[position].createTime
                    binding.itemRecNewsTitle.text = data[position].title
                    binding.itemRecNewsAuthor.text = "发起人:" + data[position].author
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl))
                            .centerCrop().into(binding.itemRecNewsCover)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                    binding.root.setOnClickListener {
                        val intent = Intent(context,RecNewsActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.recNewsList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/garbage-classification/ad-banner/list", "GET", null, true) {
                val data = g.fromJson(it, RecBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.recBanner, list)
            }
        }
    }
}