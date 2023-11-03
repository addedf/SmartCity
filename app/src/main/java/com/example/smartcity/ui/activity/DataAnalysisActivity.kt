package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityDataAnalysisBinding
import com.example.smartcity.viewBinding

class DataAnalysisActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityDataAnalysisBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.dataAnaTb.setOnClickListener {
            finish()
        }
    }
}