package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityQuestionCloselyBinding
import com.example.smartcity.viewBinding

class QuestionCloselyActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityQuestionCloselyBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.queCloTb.setOnClickListener {
            finish()
        }
        vb.queCloBtn.setOnClickListener {
            Toast.makeText(this,"提交成功",Toast.LENGTH_LONG).show()
            finish()
        }
    }
}