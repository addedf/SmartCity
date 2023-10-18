package com.example.smartcity.ui.fragment.job

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.DeliveryRecordBean
import com.example.smartcity.databinding.FragmentJDeliverBinding
import com.example.smartcity.databinding.ItemDeliveryRecordBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class JDeliverFragment : Fragment() {
    var TAG = "JDeliverFragment"
    private val vb by viewBinding(FragmentJDeliverBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadJDeList()
        return vb.root
    }

    private fun loadJDeList() {
//        TODO("api请求超时")
        tool.apply {
            send("/prod-api/api/job/deliver/list","GET",null,true) {
                val data = g.fromJson(it,DeliveryRecordBean::class.java).rows

                Log.e(TAG, "投递记录$it")
                vb.kdeList.adapter = GenericAdapter(data.size,
                    { ItemDeliveryRecordBinding.inflate(layoutInflater) }) { binding,position ->
                        binding.itemDelCompanyName.text = data[position].companyName
                    binding.itemDelMoney.text = data[position].money
                    binding.itemDelPostName.text = data[position].postName
                    binding.itemDelSatrTime.text = data[position].satrTime
                }
                vb.kdeList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}