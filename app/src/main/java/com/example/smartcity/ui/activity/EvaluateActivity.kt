package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityEvaluateBinding
import com.example.smartcity.viewBinding

class EvaluateActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityEvaluateBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.evaTb.setOnClickListener {
            finish()
        }
        vb.evaBtn.setOnClickListener {
            Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}