package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityOrderBinding

class OrderActivity : AppCompatActivity() {
    lateinit var vb : ActivityOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.orderTb.setOnClickListener {
             finish()
        }
//        TODO("pai没有返回订单内容")
    }
}