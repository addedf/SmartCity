package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.HelpListBean
import com.example.smartcity.databinding.ActivityKnowledgePolicyBinding
import com.example.smartcity.databinding.ItemKpListBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class KnowledgePolicyActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityKnowledgePolicyBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        loadList()
        vb.kpTb.setOnClickListener {
            finish()
        }
    }

    private fun loadList() {
        val list = ArrayList<HelpListBean>()
        list.add(HelpListBean("番茄种植",R.drawable.ic_kp_0))
        list.add(HelpListBean("土豆种植",R.drawable.ic_kp_1))
        list.add(HelpListBean("草莓种植",R.drawable.ic_kp_2))
        list.add(HelpListBean("西瓜种植",R.drawable.ic_kp_3))
        list.add(HelpListBean("苹果种植",R.drawable.ic_kp_4))
        list.add(HelpListBean("核桃种植",R.drawable.ic_kp_5))
        list.add(HelpListBean("小枣种植",R.drawable.ic_kp_6))
        list.add(HelpListBean("辣椒种植",R.drawable.ic_kp_7))

        tool.apply {
            vb.kpList1.adapter = GenericAdapter(list.size,
                { ItemKpListBinding.inflate(layoutInflater) }) { binding,position->
                binding.itemKpImg.setImageResource(list[position].imgId)
                binding.itemKpText.text = list[position].name
            }
            vb.kpList1.layoutManager = GridLayoutManager(context,4)
        }
    }
}