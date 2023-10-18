package com.example.smartcity.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.HelpListBean
import com.example.smartcity.databinding.FragmentHelpBinding
import com.example.smartcity.databinding.ItemHeplListBinding
import com.example.smartcity.jump
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.KnowledgePolicyActivity
import com.example.smartcity.viewBinding
import com.youth.banner.config.BannerConfig
import javax.xml.transform.Transformer

class HelpFragment : Fragment() {
    private val vb by viewBinding(FragmentHelpBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadBanner()
        loadList()
        return vb.root
    }

    private fun loadList() {
//        资源集合
        val helpList = ArrayList<HelpListBean>()
        helpList.add(HelpListBean("知识政策",R.drawable.ic_help_list0))
        helpList.add(HelpListBean("扶贫公益",R.drawable.ic_help_list6))
        helpList.add(HelpListBean("扶贫商城",R.drawable.ic_help_list1))
        helpList.add(HelpListBean("扶贫项目库",R.drawable.ic_help_list2))
        helpList.add(HelpListBean("扶贫对象",R.drawable.ic_help_list3))
        helpList.add(HelpListBean("群众求助",R.drawable.ic_help_list4))
        helpList.add(HelpListBean("地图",R.drawable.ic_help_list5))
        tool.apply {
            vb.helpRec.adapter = GenericAdapter(helpList.size,
                { ItemHeplListBinding.inflate(layoutInflater) }) { binding,position ->
                binding.itemHeplText.text = helpList[position].name
                binding.itemHelpImg.setImageResource(helpList[position].imgId)
                binding.root.setOnClickListener {
                    when(helpList[position].name) {
                        "知识政策" -> jump(KnowledgePolicyActivity::class.java)
                        else -> Toast.makeText(context,"待开发",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            // TODO("让子项之间分隔距离")
//            vb.helpRec.layoutManager = GridLayoutManager(context,2)
            vb.helpRec.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    // TODO("经过三个小时的辛勤忙碌，孟师傅决定还是不写本地图片轮播图比较好，直接放张图片就行了，专门写个本地轮播图适配器太麻烦了")
    private fun loadBanner() {
        val imageList = mutableListOf(
            R.drawable.ic_help0,
            R.drawable.ic_help1,
            R.drawable.ic_help2
        )
    }

}



