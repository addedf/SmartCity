package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityOrderAllInfoBinding
import com.example.smartcity.viewBinding

class OrderAllInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityOrderAllInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }
}