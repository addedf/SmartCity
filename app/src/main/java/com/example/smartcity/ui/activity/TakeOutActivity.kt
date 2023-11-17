package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.HelpListBean
import com.example.smartcity.bean.TakeOutBanner
import com.example.smartcity.bean.TakeOutListBean
import com.example.smartcity.bean.TakeTypeBean
import com.example.smartcity.databinding.ActivityTakeOutBinding
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.databinding.ItemTokaOutListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.fragment.tokaout.TakeOutHomeFragment
import com.example.smartcity.ui.fragment.tokaout.TakeOutMeFragment
import com.example.smartcity.ui.fragment.tokaout.TakeOutOderFragment
import com.example.smartcity.viewBinding

class TakeOutActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOutBinding::inflate)
    val TAG = "TakeOutActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.takeOutMenu.setOnNavigationItemSelectedListener {
            val fragment = when(it.itemId) {
                R.id.take_out_home -> TakeOutHomeFragment()
                R.id.take_out_order -> TakeOutOderFragment()
                else -> TakeOutHomeFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.take_out_frame,fragment).commit()
            true
        }
        supportFragmentManager.beginTransaction().replace(R.id.take_out_frame,TakeOutHomeFragment()).commit()
    }
}