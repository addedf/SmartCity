package com.example.smartcity.ui.fragment.tokaout

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
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.OrderAllBean
import com.example.smartcity.databinding.FragmentTakeOutOderBinding
import com.example.smartcity.databinding.ItemOrderAllListBinding
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.ui.activity.OrderAllInfoActivity
import com.google.android.material.tabs.TabLayout


class TakeOutOderFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutOderBinding::inflate)
    val TAG = "TakeOutOderFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadTab()
        loadAll("/prod-api/api/takeout/order/list")
        return vb.root
    }

    private fun loadTab() {
        val data = mutableListOf<String>()
        data.add("全部")
        data.add("待支付")
        data.add("待评价")
        data.add("退款")
        for (i in data.indices) {
            val newTab = vb.takeOutOrderTab.newTab()
            newTab.text = data[i]
            vb.takeOutOrderTab.addTab(newTab)
        }
        vb.takeAllLay.visibility  = View.GONE
        vb.takePayLay.visibility  = View.GONE
        vb.takeRefundLay.visibility  = View.GONE
        vb.takeEvaluateLay.visibility  = View.GONE
        vb.takeOutOrderTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                vb.takeAllLay.visibility  = View.GONE
                vb.takePayLay.visibility  = View.GONE
                vb.takeRefundLay.visibility  = View.GONE
                vb.takeEvaluateLay.visibility  = View.GONE
                if (tab != null) {
                    when(tab.position) {
                        0 -> loadAll("/prod-api/api/takeout/order/list")
                        1 -> loadAll("/prod-api/api/takeout/order/list?status=${data[tab.position]}")
                        2 -> loadAll("/prod-api/api/takeout/order/list?status=${data[tab.position]}")
                        3 -> loadAll("/prod-api/api/takeout/order/list?status=${data[tab.position]}")
                        else -> loadAll("/prod-api/api/takeout/order/list")
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun loadAll(url:String) {
        vb.takeAllLay.visibility  = View.VISIBLE
        tool.apply {
            send(url,"GET",null,true){
                val data = g.fromJson(it,OrderAllBean::class.java)
                vb.takeAllList.adapter = GenericAdapter(data.rows.size,
                    { ItemOrderAllListBinding.inflate(layoutInflater) }) { binding,position->
                    var emp = position
                    Glide.with(context).load(getUrl(data.rows[position].sellerInfo.imgUrl)).transform(RoundedCorners(5.dp)).into(binding.itemOrderAllImgUrl)
                    binding.itemOrderAllName.text = data.rows[position].sellerInfo.name
                    binding.itemOrderAllTotalPrice.text = "￥${data.rows[position].orderInfo.orderItemList[0].totalPrice}"
                    binding.itemOrderAllQuantity.text = "X${data.rows[position].orderInfo.orderItemList[0].quantity}"
                    binding.itemOrderAllStatus.text = data.rows[position].orderInfo.status
                    when(data.rows[position].orderInfo.status) {
                        "待支付" -> {
                            binding.itemOrderAllZhifu.visibility = View.VISIBLE
                        }
                        "待评价" -> {
                            binding.itemOrderAllReview.visibility = View.VISIBLE
                            binding.itemOrderAllTuikuan.visibility = View.VISIBLE
                        }
                        "完成" -> {
                            binding.itemOrderAllDoitagain.visibility = View.VISIBLE
                        }
                    }
                    binding.root.setOnClickListener {
                        val intent = Intent(context,OrderAllInfoActivity::class.java)
                        intent.putExtra("data",g.toJson(data.rows[position]))
                        startActivity(intent)
                    }
                    binding.itemOrderAllRec.adapter = GenericAdapter(data.rows[position].orderInfo.orderItemList.size,
                        { ItemServiceBinding.inflate(layoutInflater) }) { binding,position ->
                        binding.itemServiceName.text = data.rows[emp].orderInfo.orderItemList[position].productName
                        Glide.with(context).load(getUrl(data.rows[emp].orderInfo.orderItemList[position].productImage)).transform(CenterCrop(),RoundedCorners(5.dp)).into(binding.itemServiceIcon)
                    }
                    binding.itemOrderAllRec.layoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
                }
                vb.takeAllList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}