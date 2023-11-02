package com.example.smartcity.ui.fragment.retire

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentRetireNewsBinding
import com.example.smartcity.jump
import com.example.smartcity.ui.activity.RetireNewsInfoActivity
import com.example.smartcity.viewBinding

class RetireNewsFragment : Fragment()  {
    val TAG = "RetireNewsFragments"
    private val vb by viewBinding(FragmentRetireNewsBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb.retireNewsTb.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews1.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews2.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews3.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews4.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }

        vb.retireNews6.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews8.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        vb.retireNews9.setOnClickListener {
            jump(RetireNewsInfoActivity::class.java)
        }
        return vb.root
    }
}