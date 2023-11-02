package com.example.smartcity.ui.activity

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityNewsInfoBinding
import com.example.smartcity.databinding.ActivityRetireNewsInfoBinding
import com.example.smartcity.viewBinding

class RetireNewsInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRetireNewsInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.retireNewsInfoTb.setOnClickListener {
            finish()
        }
        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        vb.videView.setVideoURI(uri)
        vb.videView.start()
    }
}