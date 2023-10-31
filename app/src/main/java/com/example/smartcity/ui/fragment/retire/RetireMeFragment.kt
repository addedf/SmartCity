package com.example.smartcity.ui.fragment.retire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentRetireMeBinding
import com.example.smartcity.viewBinding

class RetireMeFragment : Fragment() {
    private val vb by viewBinding(FragmentRetireMeBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }
}