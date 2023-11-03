package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityBusPayBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class BusPayActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityBusPayBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val orderNum = intent.getStringExtra("orderNum")
        val userName = intent.getStringExtra("userName")
        val userTel = intent.getStringExtra("userTel")
        val payTime = intent.getStringExtra("payTime")
        vb.busPayOrderNum.text = orderNum.toString()
        vb.busPayPayTime.text = payTime
        vb.busPayUserName.text = userName
        vb.busPayUserTel.text = userTel
        tool.apply {
            vb.busPayBtn.setOnClickListener {
                Toast.makeText(context,"支付成功",Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}