package com.example.smartcity.ui.fragment.tokaout

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.TakeOutBanner
import com.example.smartcity.bean.TakeOutListBean
import com.example.smartcity.bean.TakeTypeBean
import com.example.smartcity.databinding.FragmentTakeOutHomeBinding
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.databinding.ItemTokaOutListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.LoginActivity
import com.example.smartcity.ui.activity.TakeOutSearchInfoActivity
import com.example.smartcity.ui.activity.TakeOutShopInfoActivity
import com.example.smartcity.viewBinding

class TakeOutHomeFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutHomeBinding::inflate)
    val TAG = "TakeOutHomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tool.checkToken {
            if (it) {
                loadBanner()
                loadType()
                loadList()
//                监听搜索框文本方法
                vb.takeOutSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        val intent = Intent(context, TakeOutSearchInfoActivity::class.java)
                        intent.putExtra("query",query)
                        startActivity(intent)
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })
            } else {
                tool.snackbar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(context, LoginActivity::class.java))
                }
            }
        }
        return vb.root
    }
    private fun loadList() {
        tool.apply {
            send("/prod-api/api/takeout/seller/list","GET",null,false) {
                val data = g.fromJson(it, TakeOutListBean::class.java).rows
                vb.takeOutList.adapter = GenericAdapter(data.size,
                    { ItemTokaOutListBinding.inflate(layoutInflater) }) { binding, position ->
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).transform(CenterCrop())
                            .into(binding.itemTakeOutImgUrl)
                    } catch (e:Exception) {
                        Log.e(TAG, "loadList: ${e.message}")
                    }
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context, TakeOutShopInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.itemTakeOutName.text = data[position].name
                    binding.itemTakeOutScore.text = "评分:${data[position].score} | 月售:${data[position].saleQuantity}"
                }
                vb.takeOutList.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }

        }
    }

    private fun loadType() {
        tool.apply {
            send("/prod-api/api/takeout/theme/list","GET",null,false) {
                val data = g.fromJson(it, TakeTypeBean::class.java).data
                vb.takeOutType.adapter = GenericAdapter(data.size,
                    { ItemServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemServiceName.text = data[position].themeName
                    Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemServiceIcon)
                }
                vb.takeOutType.layoutManager = GridLayoutManager(context,5)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/takeout/rotation/list","GET",null,false) {
                val data = g.fromJson(it, TakeOutBanner::class.java).rows
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].advImg)
                }
                setBanner(vb.takeOutBanner,list)
            }
        }
    }
}



