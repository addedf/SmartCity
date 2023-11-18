package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.OrderAllinfoBean
import com.example.smartcity.databinding.ActivityOrderAllInfoBinding
import com.example.smartcity.databinding.ItemOrderAllInfoListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class OrderAllInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityOrderAllInfoBinding::inflate)
    val TAG = "OrderAllInfoActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val data = intent.getStringExtra("data")
        val list = g.fromJson(data, OrderAllinfoBean::class.java)
        vb.orderAllInfoTb.setOnClickListener {
            finish()
        }
        vb.orderAllInfoName.text = list.sellerInfo.name
        vb.orderAllInfoAddress.text = list.sellerInfo.address
        vb.orderAllInfoCreateTime.text = list.orderInfo.createTime
        vb.orderAllInfoOpaymentType.text = "电子钱包"
        vb.orderAllInfoOrderNo.text = list.orderInfo.orderNo
        vb.orderAllInfoReceiverPhone.text = list.orderInfo.receiverPhone
        vb.orderAllInfoStatus.text = list.orderInfo.status
        tool.apply {
            vb.orderAllInfoRec.adapter = GenericAdapter(list.orderInfo.orderItemList.size,
                { ItemOrderAllInfoListBinding.inflate(layoutInflater) }) { binding,position ->
                binding.itemOrderAllInfoProductName.text = list.orderInfo.orderItemList[position].productName
                binding.itemOrderAllInfoQuantity.text = list.orderInfo.orderItemList[position].quantity.toString() + "份"
                binding.itemOrderAllInfoTotalPrice.text = list.orderInfo.orderItemList[position].productPrice.toString()
                Glide.with(context).load(getUrl(list.orderInfo.orderItemList[position].productImage)).into(binding.itemOrderAllInfoProductImage)
            }
            vb.orderAllInfoRec.layoutManager = LinearLayoutManager(context)
        }
    }
}