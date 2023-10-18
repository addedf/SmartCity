package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityVolunteerBinding
import com.example.smartcity.tool
import com.example.smartcity.ui.fragment.von.MeVolunteerFragment
import com.example.smartcity.ui.fragment.von.NewsVolunteerkFragment
import com.example.smartcity.ui.fragment.von.VolunteerkFragment
import com.example.smartcity.viewBinding

class VolunteerActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityVolunteerBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.root.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                vb.vonNav.setOnNavigationItemSelectedListener {
                    val fragment = when (it.itemId) {
                        R.id.von_news_volunteer -> NewsVolunteerkFragment()
                        R.id.von_volunteer -> VolunteerkFragment()
                        R.id.von_me_volunteer -> MeVolunteerFragment()
                        else -> NewsVolunteerkFragment()
                    }
                    supportFragmentManager.beginTransaction().replace(R.id.von_content, fragment)
                        .commit()
                    true
                }
                supportFragmentManager.beginTransaction().replace(R.id.von_content, NewsVolunteerkFragment())
                    .commit()
            } else {
                tool.snackbar(vb.root,"未登录","去登录") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }
}