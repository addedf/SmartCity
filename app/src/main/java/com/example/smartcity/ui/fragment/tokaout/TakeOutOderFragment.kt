package com.example.smartcity.ui.fragment.tokaout

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.OrderAllBean
import com.example.smartcity.databinding.FragmentTakeOutOderBinding
import com.example.smartcity.databinding.ItemOrderAllListBinding
import com.google.android.material.tabs.TabLayout


class TakeOutOderFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutOderBinding::inflate)
    val TAG = "TakeOutOderFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadTab()
        loadAll()
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
                        0 -> loadAll()
                        1 -> loadPay()
                        2 -> loadEvaluate()
                        3 -> loadRefund()
                        else -> loadAll()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun loadAll() {
        vb.takeAllLay.visibility  = View.VISIBLE
        tool.apply {
            send("/prod-api/api/takeout/order/list","GET",null,true){
                val data = g.fromJson(it,OrderAllBean::class.java).rows
//                Log.e(TAG, "loadAll: ${data.size}")
//                val index = data.size
//                Log.e(TAG, "loadAll: $index", )
                vb.takeAllList.adapter = GenericAdapter(10,
                    { ItemOrderAllListBinding.inflate(layoutInflater) }) { binding,position->
                    Glide.with(context).load(getUrl(data[position].sellerInfo.imgUrl)).transform(RoundedCorners(5.dp)).into(binding.itemOrderAllImgUrl)
                    binding.itemOrderAllName.text = data[position].sellerInfo.name
                    binding.itemOrderAllTotalPrice.text = "￥${data[position].orderInfo.orderItemList[0].totalPrice}"
                    binding.itemOrderAllQuantity.text = "X${data[position].orderInfo.orderItemList[0].quantity}"
                    when(data[position].orderInfo.status) {
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
                }
                vb.takeAllList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
    private fun loadPay() {
        vb.takePayLay.visibility  = View.VISIBLE
    }
    private fun loadRefund() {
        vb.takeRefundLay.visibility  = View.VISIBLE
    }
    private fun loadEvaluate() {
        vb.takeEvaluateLay.visibility  = View.VISIBLE
    }
}