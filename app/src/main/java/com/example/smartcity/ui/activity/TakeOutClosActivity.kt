package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.smartcity.R
import com.example.smartcity.bean.TakeOrderingFoodListBean
import com.example.smartcity.databinding.ActivityTakeOutClosBinding
import com.example.smartcity.g
import com.example.smartcity.viewBinding

class TakeOutClosActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOutClosBinding::inflate)
    val TAG = "TakeOutClosActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
//        val data = intent.getStringExtra("data")
//        val list = g.fromJson(data,TakeOrderingFoodListBean::class.java)
//        val index = intent.getIntExtra("index",0)
//        val price = intent.getStringExtra("price")
//        val name = intent.getStringExtra("name")
//        Log.e(TAG, "onCreate: $index,$price,$name")
    }
}