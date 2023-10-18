package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.MovieBean
import com.example.smartcity.bean.MovieInfoActivity
import com.example.smartcity.bean.MovieListBean
import com.example.smartcity.databinding.ActivityMovieBinding
import com.example.smartcity.databinding.ItemMovieBinding
import com.example.smartcity.dp
import com.example.smartcity.g
import com.example.smartcity.tool
import java.lang.Exception

class MovieActivity : AppCompatActivity() {
    lateinit var vb: ActivityMovieBinding
    val TAG = "MovieActivity"
//    默认传入空字符串  查询不到就加载全部
    var filter = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.moTb.setOnClickListener {
            finish()
        }
//        加载电影列表
//        搜索功能
        vb.moSearchBar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            //            提交输入的文本
            override fun onQueryTextSubmit(p0: String?): Boolean {
//                传入加载列表
                if (p0 != null) {
                    filter = p0
                }
                loadList(filter)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean = true
        })
        loadList(filter)
        tool.checkToken {
            if (it) {
//                轮播图
                loadBanner()
//                电影列表
                loadList(filter)
            } else {
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    private fun loadList(filter: String) {
        tool.apply {
            send("/prod-api/api/movie/film/list", "GET", null, false) {
                val list = g.fromJson(it, MovieListBean::class.java).rows
//                默认加载全部
                val data = mutableListOf<MovieListBean.Rows>()
                for (d in list) {
//                    遍历实体类接收的name参数 判断和输入的一样添加到  data 展示到列表
                    if (d.name.contains(filter) || d.playDate.contains(filter)) {
                        data.add(d)
                    }
                }
                vb.moList.adapter = GenericAdapter(data.size,
                    { ItemMovieBinding.inflate(layoutInflater) }) { binding, position ->
//                    拉伸布局
                    binding.root.layoutParams = RecyclerView.LayoutParams(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT
                    )
//                    点击跳转 详情
                    binding.root.setOnClickListener {
                        val intent = Intent(context,MovieInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.moItemName.text = data[position].name
                    binding.moItemDuration.text = "时长:${data[position].duration}"
                    binding.moItemPlayData.text = "上映时间:${data[position].playDate}"
                    try {
                        Glide.with(context).load(getUrl(data[position].cover))
                            .error(getDrawable(R.drawable.chengshi))
                            .transform(CenterCrop(),RoundedCorners(5.dp))
                            .into(binding.moItemCover)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.moList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = true
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/movie/rotation/list", "GET", null, false) {
                val data = g.fromJson(it, MovieBean::class.java).rows
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].advImg)
                }
                setBanner(vb.moBanner, list)
            }
        }
    }
}