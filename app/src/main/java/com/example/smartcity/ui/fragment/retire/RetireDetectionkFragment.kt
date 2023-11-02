package com.example.smartcity.ui.fragment.retire

import android.app.SearchManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentRetireDetectionkBinding
import com.example.smartcity.jump
import com.example.smartcity.ui.activity.DetectionkInfoActivity
import com.example.smartcity.viewBinding

class RetireDetectionkFragment : Fragment() {
    val TAG = "TAG"
    private val vb by viewBinding(FragmentRetireDetectionkBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb.petMeTb.setOnClickListener {
            jump(DetectionkInfoActivity::class.java)
        }
        vb.detet1.setOnClickListener {
            jump(DetectionkInfoActivity::class.java)
        }
        vb.detet2.setOnClickListener {
            jump(DetectionkInfoActivity::class.java)
        }
        vb.detet3.setOnClickListener {
            jump(DetectionkInfoActivity::class.java)
        }
        vb.detet4.setOnClickListener {
            jump(DetectionkInfoActivity::class.java)
        }
        return vb.root
    }
}