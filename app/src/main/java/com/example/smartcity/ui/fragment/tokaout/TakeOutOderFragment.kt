package com.example.smartcity.ui.fragment.tokaout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentTakeOutOderBinding
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout


class TakeOutOderFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutOderBinding::inflate)
    private lateinit var takeOutAllFragment: TakeOutAllFragment
    private lateinit var takeOutUnpaidFragment: TakeOutUnpaidFragment
    private lateinit var takeOutAppraiseFragment: TakeOutAppraiseFragment
    private lateinit var takeOutRefundFragment: TakeOutRefundFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadTab()
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
        vb.takeOutOrderTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                if (tab != null) {
//                    val fragment = when(tab.position) {
//
//                        else ->
//                    }
//                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}