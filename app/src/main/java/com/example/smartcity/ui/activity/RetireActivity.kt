package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityRetireBinding
import com.example.smartcity.ui.fragment.retire.RetireDetectionkFragment
import com.example.smartcity.ui.fragment.retire.RetireHomeFragment
import com.example.smartcity.ui.fragment.retire.RetireMeFragment
import com.example.smartcity.ui.fragment.retire.RetireNewsFragment
import com.example.smartcity.viewBinding

class RetireActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRetireBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.retireNav.setOnNavigationItemSelectedListener {
            val fragment = when(it.itemId) {
                R.id.retire_home -> RetireHomeFragment()
                R.id.retire_news -> RetireNewsFragment()
                R.id.retire_detection -> RetireDetectionkFragment()
                R.id.retire_me -> RetireMeFragment()
                else -> RetireHomeFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.retire_content,fragment).commit()
            true
        }
        supportFragmentManager.beginTransaction().replace(R.id.retire_content,RetireHomeFragment()).commit()
    }
}