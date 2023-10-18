package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityJobBinding
import com.example.smartcity.tool
import com.example.smartcity.ui.fragment.job.JDeliverFragment
import com.example.smartcity.ui.fragment.job.JHomeFragment
import com.example.smartcity.ui.fragment.job.JMeFragment
import com.example.smartcity.viewBinding

class JobActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityJobBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.jobTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                vb.jobNav.setOnNavigationItemSelectedListener {
                    val fragment = when (it.itemId) {
                        R.id.nav_JobSearch -> JHomeFragment()
                        R.id.nav_Deliver -> JDeliverFragment()
                        R.id.CurriculumVitae -> JMeFragment()
                        else -> JHomeFragment()
                    }
                    supportFragmentManager.beginTransaction().replace(R.id.job_container, fragment)
                        .commit()
                    true
                }
                supportFragmentManager.beginTransaction()
                    .replace(R.id.job_container, JHomeFragment()).commit()
            } else {
                tool.snackbar(vb.root, "未登录", "去登陆") {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }
}

