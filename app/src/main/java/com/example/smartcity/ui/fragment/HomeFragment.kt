package com.example.smartcity.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.*
import com.example.smartcity.databinding.FragmentHomeBinding
import com.example.smartcity.databinding.ItemHotBinding
import com.example.smartcity.databinding.ItemNewsListBinding
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.NewsActivity
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import java.lang.Exception

class HomeFragment : Fragment() {
    lateinit var vb: FragmentHomeBinding
    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentHomeBinding.inflate(layoutInflater)
//        轮播图方法
        loadBanner()
//        服务方法
        loadService()
//        新闻分类
        loadTab()
//        热门主题
        loadHot()
//        碎片中获取上下文
        val context = requireContext()
        return vb.root
    }

    private fun loadHot() {
        tool.apply {
            send("/prod-api/press/press/list?hot=Y", "GET", null, false) {
                val data = g.fromJson(it, HotBean::class.java).rows
                val adapter = GenericAdapter(2,
                    { ItemHotBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemHotTitle.text = data[position].title
                    binding.root.setOnClickListener {
                        val intent = Intent(context, NewsActivity::class.java)
                        intent.putExtra(
                            "content",
                            "<style>img{width:100vw}</style>${
                                data[position].content.replace(
                                    "src=\"",
                                    "src=\"http://${get("server")}:${get("port")}"
                                )
                            }"
                        )
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].cover))
                            .transform(CenterCrop(), RoundedCorners(5.dp))
                            .into(binding.itemHotCover)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.homeHot.adapter = adapter
                vb.homeHot.layoutManager = GridLayoutManager(context, 2)
            }
        }
    }


    private fun loadBanner() {
//        apply 构造Activity方法身上的tool实例代作为上下文默认调用
        tool.apply {
            send("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", "GET", null, false) {
                val bean = Gson().fromJson(it, BannerBean::class.java).rows
//                可变的字符串的元素集合
                val list = mutableListOf<String>()
//                遍历bean类拿到图片
                for (i in bean.indices) {
                    list.add(bean[i].advImg)
                }
//                把图片集合和组件id放入setBanner
                setBanner(vb.homeBanner, list)
            }
        }
    }

    //    TODO("服务点击跳转未完成")
    private fun loadService() {
        tool.apply {
            send("/prod-api/api/service/list", "GET", null, false) { it ->
//                降序sortedByDescending 升序sortedBy 根据sort数值权重进行排序
                val sortedDataItemsDescending =
                    Gson().fromJson(it, ServiceBean::class.java).rows.sortedByDescending { it.sort }
                val adapter = GenericAdapter(
                    10,
                    { ItemServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    if (position == 9) {
                        binding.itemServiceName.text = "更多服务"
                    } else {
                        binding.itemServiceName.text =
                            sortedDataItemsDescending[position].serviceName
                    }
                    Glide.with(context).load(getUrl(sortedDataItemsDescending[position].imgUrl))
                        .transform(
                            RoundedCorners(5.dp),
                            CenterCrop()
                        ).into(binding.itemServiceIcon)
                }
//                设置适配器
                vb.homeService.adapter = adapter
//                设置布局
                vb.homeService.layoutManager = GridLayoutManager(context, 5)
            }
        }
    }

    private fun loadTab() {
        tool.apply {
            send("/prod-api/press/category/list", "GET", null, false) {
                val data = Gson().fromJson(it, NewsTabBean::class.java).data
                for (tab in data) {
                    val newTab = vb.homeTab.newTab()
                    newTab.text = tab.name
//                    把json的标题栏解析添加到组件中
                    vb.homeTab.addTab(newTab)
                }
//                监视标题栏点击
                vb.homeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        if (tab != null) {
//                            点击传入id
                            loadList(data[tab.position].id)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                })
//                判断传递的集合不为空
                if (data.isNotEmpty()) {
                    loadList(data[0].id)
                }
            }
        }
    }

    //    接收传入的id  SuppressLint告诉编译器这段代码中的文本不需要翻译针对WebView组件
    @SuppressLint("SetTextI18n")
    private fun loadList(id: Int) {
        tool.apply {
            send("/prod-api/press/press/list?type=$id", "GET", null, false) {
                val data = Gson().fromJson(it, NewsListBean::class.java).rows
                val adapter = GenericAdapter(
                    data.size,
                    { ItemNewsListBinding.inflate(layoutInflater) }) { binding, position ->
//                        正则表达式 匹配html标签删除
                    val reg = "<[^<]+?>|<p>|&nbsp;|</p>|<p>[\\\\s\\\\S]*?</p>|&nbsp;"
                    binding.newsItemTitle.text = data[position].title
                    binding.newsItemDate.text = "发布时间:${data[position].createTime}"
                    binding.newsItemCommit.text = "${data[position].commentNum}评论"
//                    replace替换字符串方法第一个参数放需要匹配的参数用空字符串代替
                    binding.newsItemContent.text =
                        data[position].content.replace(Regex(reg), "").replace(" ", "")
//                    点击跳转详情界面
                    binding.root.setOnClickListener {
                        val intent = Intent(context, NewsActivity::class.java)
                        intent.putExtra("title", data[position].title)
                        intent.putExtra(
                            "content",
                            "<style>img{width:100vw}</style>${
                                data[position].content.replace(
                                    "src=\"",
                                    "src=\"http://${get("server")}:${get("port")}"
                                )
                            }"
                        )
                        startActivity(intent)
                    }
//                    加载图片
                    try {
                        Glide.with(context).load(getUrl(data[position].cover))
                            .transform(CenterCrop(), RoundedCorners(5.dp))
                            .into(binding.newsItemCover)
                    } catch (e: Exception) {
                        Log.e(TAG, "图片加载失败：${e.message}")
                    }
                }
                vb.homeNewsList.adapter = adapter
                vb.homeNewsList.layoutManager = object : LinearLayoutManager(context) {
                    //                    重写canScrollVertically 禁止上下滑动
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}