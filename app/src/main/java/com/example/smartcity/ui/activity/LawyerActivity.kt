package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityLawyerBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class LawyerActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityLawyerBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.lawyerTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
            } else {
                tool.snackbar(vb.root,"未登录","去登录") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/ad-banner/list","GET",null,true) {

            }
        }
    }
}