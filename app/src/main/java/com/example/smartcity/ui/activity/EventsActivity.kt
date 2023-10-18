package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.EventsBannerBean
import com.example.smartcity.bean.EventsListBean
import com.example.smartcity.bean.EventsTypeBean
import com.example.smartcity.databinding.ActivityEventsBinding
import com.example.smartcity.databinding.ItemEventsListBinding
import com.google.android.material.tabs.TabLayout
import java.lang.Exception

class EventsActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityEventsBinding::inflate)
    val TAG = "EventsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.evenTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                loadType()
                loadList("商务")
            } else {
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadType() {
        tool.apply {
            send("/prod-api/api/activity/category/list", "GET", null, false) {
                val data = g.fromJson(it, EventsTypeBean::class.java).data
                val tyPeSet = HashSet<String>()
                for (tab in data) {
                    val eventsType = tab.name
                    if (tyPeSet.add(eventsType)) {
                        val newTab = vb.evenTab.newTab()
                        newTab.text = eventsType
                        vb.evenTab.addTab(newTab)
                    }
                }
                vb.evenTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        val selectedEventsType =
                            tab?.position?.let { it1 -> vb.evenTab.getTabAt(it1)?.text.toString() }
                        if (selectedEventsType != null) {
                            loadList(selectedEventsType)
                        }
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {
                    }

                    override fun onTabReselected(tab: TabLayout.Tab?) {
                    }

                })
            }
        }
    }

    private fun loadList(type: String) {
        tool.apply {
            send("/prod-api/api/activity/activity/list", "GET", null, false) {
                val data = g.fromJson(it, EventsListBean::class.java).rows
                val filterType = data.filter { it.categoryName == type }
                vb.evenList.adapter = GenericAdapter(filterType.size,
                    { ItemEventsListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemEvenName.text = filterType[position].name
                    binding.itemEvenCreateTime.text = "创建时间:" + filterType[position].createTime
                    binding.itemEvenLikeNum.text = "喜欢人数:" + filterType[position].likeNum
                    binding.root.layoutParams = RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
//                    点击跳转
                    binding.root.setOnClickListener {
                        val intent = Intent(context, EventsInfoActivity::class.java)
                        intent.putExtra("id", filterType[position].id)
                        intent.putExtra("likeNum", filterType[position].likeNum)
                        intent.putExtra("createTime", filterType[position].createTime)
                        intent.putExtra("name", filterType[position].name)
                        intent.putExtra(
                            "content", "<style>img{width:100vw}</style>${
                                data[position].content.replace(
                                    "src=\"",
                                    "src=\"http://${get("server")}:${get("port")}"
                                )
                            }"
                        )
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(filterType[position].imgUrl))
                            .transform(RoundedCorners(5.dp))
                            .into(binding.itemEvenImgUrl)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.evenList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/activity/rotation/list", "GET", null, true) {
                val data = g.fromJson(it, EventsBannerBean::class.java).rows
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].advImg)
                }
                setBanner(vb.evenBanner, list)
            }
        }
    }
}