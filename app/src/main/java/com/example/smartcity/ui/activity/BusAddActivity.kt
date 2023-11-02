package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityBusAddBinding
import com.example.smartcity.viewBinding

class BusAddActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityBusAddBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
    }
}