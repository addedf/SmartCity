package com.example.smartcity.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.bean.NewsList2Bean
import com.example.smartcity.bean.NewsListBean
import com.example.smartcity.bean.NewsTabBean
import com.example.smartcity.databinding.FragmentNewsBinding
import com.example.smartcity.databinding.ItemNewsListBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.NewsInfoActivity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import java.lang.Exception

class NewsFragment : Fragment() {
    lateinit var vb: FragmentNewsBinding
    val TAG = "NewsFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentNewsBinding.inflate(layoutInflater)
//        轮播图方法
        loadBanner()
//        tab栏方法
        loadTab()
        return vb.root
    }

    private fun loadTab() {
        tool.apply {
            send("/prod-api/press/category/list", "GET", null, false) {
                val data = g.fromJson(it, NewsTabBean::class.java).data
                for (tab in data) {
                    val newTab = vb.newsTab.newTab()
                    newTab.text = tab.name
                    vb.newsTab.addTab(newTab)
                }
                vb.newsTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        try {
                            if (tab != null) {
                                loadList(data[tab.position].id)
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "${e.message}")
                            e.printStackTrace()
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {

                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {

                    }

                })
//                默认加载
                try {
                    if (data.isNotEmpty()) {
                        loadList(data[0].id)
                    }
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
//    加载新闻列表方法
    private fun loadList(id: Int) {
        tool.apply {
            send("/prod-api/press/press/list?type=$id","GET",null,false) {
                val data = g.fromJson(it,NewsList2Bean::class.java).rows
                val adapter = GenericAdapter(data.size,
                    { ItemNewsListBinding.inflate(layoutInflater) }) { binding,position ->
//                    正则表达式
                    val reg = "<[^<]+?>|<p>|&nbsp;|</p>|<p>[\\\\s\\\\S]*?</p>|&nbsp;"
                    binding.newsItemTitle.text = data[position].title
                    binding.newsItemDate.text = "发布时间:${data[position].publishDate}"
                    binding.newsItemCommit.text = "${data[position].commentNum}评论"
                    binding.newsItemContent.text = data[position].content.replace(Regex(reg),"")
                    try {
                        Glide.with(context).load(getUrl(data[position].cover))
                            .transform(CenterCrop(),RoundedCorners(5.dp))
                            .into(binding.newsItemCover)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
//                    点击跳转界面
                    binding.root.setOnClickListener {
                        val intent = Intent(context,NewsInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.newsList.adapter = adapter
                vb.newsList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadBanner() {
        //        apply 构造Activity方法身上的tool实例代作为上下文默认调用
        tool.apply {
            send("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", "GET", null, false) {
                val bean = Gson().fromJson(it, BannerBean::class.java)
                val list = mutableListOf<String>()
                for (i in bean.rows.indices) {
                    list.add(bean.rows[i].advImg)
                }
                setBanner(vb.newsBanner, list)
            }
        }
    }
}