package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.Tool
import com.example.smartcity.databinding.ActivityCarOrderBinding
import com.example.smartcity.viewBinding

class CarOrderActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityCarOrderBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.carOrderTb.setOnClickListener {
            finish()
        }
        vb.carOrderBtn.setOnClickListener {
            Toast.makeText(this,"预约成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}