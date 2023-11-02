package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityDetectionkInfoBinding
import com.example.smartcity.viewBinding

class DetectionkInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityDetectionkInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.detetTb.setOnClickListener {
            finish()
        }
    }
}