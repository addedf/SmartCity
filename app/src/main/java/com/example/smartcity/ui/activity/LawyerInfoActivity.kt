package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityLawyerInfoBinding
import com.example.smartcity.viewBinding

class LawyerInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityLawyerInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.lawyerInfoTb.setOnClickListener {
            finish()
        }
        
    }
}