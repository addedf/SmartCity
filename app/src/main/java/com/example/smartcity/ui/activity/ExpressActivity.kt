package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.ExpressBannerAdapter
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.ExpressBannerBean
import com.example.smartcity.bean.ExpressBean
import com.example.smartcity.databinding.ActivityExpressBinding
import com.example.smartcity.databinding.ItemExpressListBinding
import com.example.smartcity.databinding.ItemExpressRecommendBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import java.lang.Exception

class ExpressActivity : AppCompatActivity() {
    lateinit var vb: ActivityExpressBinding
    val TAG = "ExpressActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityExpressBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.expressTb.setOnClickListener {
            finish()
        }
//        验证是否登录
        tool.checkToken {
            if (it) {
//                登录加载方法
                loadBanner()
//                推荐物流公司
                loadRecommend()
//                物流公司列表
                loadList()
            } else {
                Toast.makeText(this, "未登录", Toast.LENGTH_SHORT).show()
            }
        }
//        获取搜索文本数据
        // TODO("问老师如何点击软键盘搜索按钮搜索默认值 SearchView无法继承EditText方法使用回车确认搜索")
        vb.expressSearch.setOnSearchClickListener{
            Log.e(TAG, "${vb.expressSearch.queryHint.toString()}")
        }
        vb.expressSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //            有交互操作 在这里进行搜索操作
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val searchText = p0 ?: vb.expressSearch.queryHint.toString()
                performSearch(searchText)
                return false
            }

            //            false 表示不需要监听文本输入变化
            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }
        })
    }

    private fun performSearch(p0: String?) {
        tool.apply {
            val intent = Intent(context, SearchContentActivity::class.java)
            intent.putExtra("OddNumber", p0)
            startActivity(intent)
        }

    }

    //    推荐列表物流公司
    private fun loadList() {
//        以下获取的是除第一页获取的物流公司分页2
        tool.apply {
            send(
                "/prod-api/api/logistics-inquiry/logistics_company/list?pageSize=15&pageNum=2",
                "GET",
                null,
                true
            ) {
                val data = g.fromJson(it, ExpressBean::class.java).data
                val adapter = GenericAdapter(data.size,
                    { ItemExpressListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.setOnClickListener {
                        val intent = Intent(context, ExpressInfoActivity::class.java)
                        intent.putExtra("id", data[position].id)
                        startActivity(intent)
                    }
                    binding.itemExpressListName.text = data[position].name
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).circleCrop()
                            .into(binding.itemExpressListIcon)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
//                外层已经包裹了ScrollView用于垂直滚动 不需要列表有滚动功能只需要展示 因此禁止列表的垂直滚动功能
                val layoutManager: LinearLayoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
                vb.expressList.adapter = adapter
                vb.expressList.layoutManager = layoutManager
            }
        }
    }

    //    推荐物流公司
    private fun loadRecommend() {
        tool.apply {
//            题库要求展示三行 一行4个 所以根据文档获取12个物流公司 pageNum限制页码不允许一下提取全部信息
            send(
                "/prod-api/api/logistics-inquiry/logistics_company/list?pageSize=12&pageNum=1",
                "GET", null, true
            ) {
                val data = g.fromJson(it, ExpressBean::class.java).data
                val adapter = GenericAdapter(data.size,
                    { ItemExpressRecommendBinding.inflate(layoutInflater) }) { binding, position ->
//                    点击跳转物流公司界面
                    binding.root.setOnClickListener {
                        val intent = Intent(context, ExpressInfoActivity::class.java)
                        intent.putExtra("id", data[position].id)
                        startActivity(intent)
                    }
                    binding.itemRecommendName.text = data[position].name
                    try {
//                        circleCrop  圆形裁剪
                        Glide.with(context).load(getUrl(data[position].imgUrl)).circleCrop()
                            .into(binding.itemRecommendIcon)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                val layoutManager = GridLayoutManager(context, 4)
                vb.expressRecommend.adapter = adapter
                vb.expressRecommend.layoutManager = layoutManager
            }
        }
    }

    //    轮播图
    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/logistics-inquiry/ad-banner/list", "GET", null, true) {
                val data = g.fromJson(it, ExpressBannerBean::class.java).data
                val adapter = ExpressBannerAdapter(context, data)
                vb.expressBanner.adapter = adapter
            }
        }
    }
}

