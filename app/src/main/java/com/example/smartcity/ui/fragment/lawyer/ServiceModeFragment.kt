package com.example.smartcity.ui.fragment.lawyer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.LawyerInfoBean
import com.example.smartcity.databinding.FragmentServiceModeBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding


class ServiceModeFragment : Fragment() {
    val TAG = "ServiceModeFragment"
    companion object {
        fun newInstance(id: Int): ServiceModeFragment {
            val fragment = ServiceModeFragment()
            val bundle = Bundle()
            bundle.putInt("id", id)
            fragment.arguments = bundle
            return fragment
        }
    }
    private val vb by viewBinding(FragmentServiceModeBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val id = arguments?.getInt("id", 0)
        loadContent(id)
        return vb.root
    }

    private fun loadContent(id: Int?) {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/${id}", "GET", null, true) {
                val data = g.fromJson(it, LawyerInfoBean::class.java).data
                Glide.with(context).load(getUrl(data.avatarUrl)).into(vb.serviceAvatarUrl)
                vb.serviceEduInfo.text = "教育经历:${data.eduInfo}"
                vb.serviceLegalExpertiseId.text = "从业年数:${data.legalExpertiseId}"
                vb.serviceLegalExpertiseName.text = "擅长领域:${data.legalExpertiseName}"
                vb.serviceLicenseNo.text = "联系方式:${data.licenseNo}"
            }
        }
    }

}