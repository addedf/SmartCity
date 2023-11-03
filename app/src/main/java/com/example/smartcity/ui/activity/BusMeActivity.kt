package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BusOrderListBean
import com.example.smartcity.databinding.ActivityBusMeBinding
import com.example.smartcity.databinding.ItemBusOrderListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class BusMeActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityBusMeBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.busMeTb.setOnClickListener {
            finish()
        }
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/bus/order/list","GET",null,true) {
                val data = g.fromJson(it,BusOrderListBean::class.java).rows
                vb.busMeList.adapter = GenericAdapter(data.size,
                    { ItemBusOrderListBinding.inflate(layoutInflater) }) { binding, postiton->
                    binding.busOrderOrderNum.text = "订单号:${data[postiton].orderNum}"
                    binding.busOrderStart.text = "起点:${data[postiton].start}"
                    binding.busOrderEnd.text = "终点:${data[postiton].end}"
                    binding.busOrderPath.text = "路线:${data[postiton].path}"
                    binding.busOrderPrice.text = "票价:${data[postiton].price}"
                    binding.busOrderPaymentType.text = data[postiton].paymentType
                    binding.root.setOnClickListener {
                        val intent = Intent(context,BusPayActivity::class.java)
                        intent.putExtra("orderNum",data[postiton].orderNum)
                        intent.putExtra("userName",data[postiton].userName)
                        intent.putExtra("userTel",data[postiton].userTel)
                        intent.putExtra("payTime",data[postiton].payTime)
                        startActivity(intent)
                    }
                }
                vb.busMeList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}