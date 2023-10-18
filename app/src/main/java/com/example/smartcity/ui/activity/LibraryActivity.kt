package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LibraryListBean
import com.example.smartcity.databinding.ActivityLibraryBinding
import com.example.smartcity.databinding.ItemLibListBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import java.lang.Exception

class LibraryActivity : AppCompatActivity() {
    lateinit var vb: ActivityLibraryBinding
    val TAG = "LibraryActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(vb.root)
//        该页面有评论功能因此需要判断用户是否登录
        loadData()
        vb.libTb.setOnClickListener {
            finish()
        }
    }
//    登录完成再次加载数据 从后台到前台的触发的声明周期
    override fun onResume() {
        loadData()
        super.onResume()
    }

    private fun loadData() {
        tool.checkToken {
            if (it) {
//                登录才加载图书馆列表
                loadList()
            } else {
//                没有登录则显示去登录按钮
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    //    图书馆列表
    private fun loadList() {
        tool.apply {
            send(
                "/prod-api/api/digital-library/library/list?pageNum=1&pageSize=10",
                "GET",
                null,
                true
            ) {
//                g是Gosn的封装缩写   根据要求根据businessState字段优先展示营业中的图书馆 ?代表允许空指针不崩溃
                val data = g.fromJson(
                    it,
                    LibraryListBean::class.java
                )?.rows?.sortedByDescending { it.businessState == "1" }
                vb.libList.adapter =
//                    断言类似于使用前的校验判断传入的内容是否为空 为空则不去执行 不为空才执行 这样传入的内容空指针也不会崩溃
                    data?.let { it1 ->
                        GenericAdapter(it1.size,
                            { ItemLibListBinding.inflate(layoutInflater) }) { binding, position ->
                            binding.root.layoutParams = RecyclerView.LayoutParams(
                                //                        宽度填充 高度自适应
                                RecyclerView.LayoutParams.MATCH_PARENT,
                                RecyclerView.LayoutParams.WRAP_CONTENT
                            )
                            binding.libItemAddress.text = data[position].address
                            binding.libItemName.text = data[position].name
                            binding.libItemBusTime.text = data[position].businessHours
                            //                    businessState使用when判断是否营业
                            binding.libItemBusStatus.text = when (data[position].businessState) {
                                "0" -> "未营业"
                                else -> "正在营业"
                            }
                            try {
                                Glide.with(context).load(getUrl(data[position].imgUrl))
                                    .transform(CenterCrop(), RoundedCorners(5.dp))
                                    .into(binding.libItemCover)
                            } catch (e: Exception) {
                                Log.e(TAG, "${e.message}")
                            }
                            //                    点击跳转到详情页面
                            binding.root.setOnClickListener {
//                                点击把当前的id也传入到下个界面中
                                val intent = Intent(context,LibraryInfoActivity::class.java)
                                intent.putExtra("id",data[position].id)
                                startActivity(intent)
                            }
                        }
                    }
                vb.libList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}