package com.example.smartcity.ui.fragment.tokaout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentTakeOutOderBinding
import com.example.smartcity.viewBinding


class TakeOutOderFragment : Fragment() {
    private val vb by viewBinding(FragmentTakeOutOderBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }
}