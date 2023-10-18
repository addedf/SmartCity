package com.example.smartcity.ui.fragment.von

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentMeVilunteerBinding
import com.example.smartcity.viewBinding

class MeVolunteerFragment : Fragment() {
 private val vb by viewBinding(FragmentMeVilunteerBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return vb.root
    }


}