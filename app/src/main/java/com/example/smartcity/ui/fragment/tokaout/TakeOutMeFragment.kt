package com.example.smartcity.ui.fragment.tokaout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentTakeOutMeBinding
import com.example.smartcity.viewBinding

class TakeOutMeFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutMeBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }
}