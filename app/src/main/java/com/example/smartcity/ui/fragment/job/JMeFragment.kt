package com.example.smartcity.ui.fragment.job

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.FragmentJMeBinding
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.AddCurriculumVitaeActivity
import com.example.smartcity.ui.activity.CurriculumVitaeActivity
import com.example.smartcity.ui.activity.EditCurriculumVitaeActivity
import com.example.smartcity.viewBinding

class JMeFragment : Fragment() {
    private val vb by viewBinding(FragmentJMeBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tool.apply {
            vb.jmeCurriculumVitae.setOnClickListener {
                context?.startActivity(Intent(context,CurriculumVitaeActivity::class.java))
            }
            vb.jmeAdd.setOnClickListener {
                context?.startActivity(Intent(context,AddCurriculumVitaeActivity::class.java))
            }
            vb.jmeEdit.setOnClickListener {
                context?.startActivity(Intent(context,EditCurriculumVitaeActivity::class.java))
            }
        }
        return vb.root
    }
}