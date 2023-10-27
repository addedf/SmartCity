package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.LawyerBean
import com.example.smartcity.bean.LawyerInfoBean
import com.example.smartcity.databinding.ActivityLawyerInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.fragment.lawyer.ServiceModeFragment
import com.example.smartcity.ui.fragment.lawyer.ServiceModeFragment.Companion.newInstance
import com.example.smartcity.ui.fragment.lawyer.UserEvaluationFragment
import com.example.smartcity.viewBinding
import com.google.android.material.tabs.TabLayout
import java.lang.reflect.Array.newInstance

class LawyerInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityLawyerInfoBinding::inflate)
    val TAG = "LawyerInfoActivity"

    //    先声明两个全局碎片类型 延迟赋值  注意这里是全局的 下方加载不早需要单独new
    private lateinit var serviceModeFragment: ServiceModeFragment
    private lateinit var userEvaluationFragment: UserEvaluationFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.lawyerInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id", 0)
//        向两个碎片的伴生方法中传入id
        serviceModeFragment = ServiceModeFragment.newInstance(id)
        userEvaluationFragment = UserEvaluationFragment.newInstance(id)
//        提交
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.lawyer_info_container, userEvaluationFragment)
        transaction.add(R.id.lawyer_info_container, serviceModeFragment)
        transaction.commit()
        loadContent(id)
//        填充数据
        val data = mutableListOf<String>()
        data.add("服务方式")
        data.add("用户评价")
        for (tab in data.indices) {
            val newTab = vb.lawyerInfoTab.newTab()
            newTab.text = data[tab]
            vb.lawyerInfoTab.addTab(newTab)
        }
        loadTab()
    }

    private fun loadTab() {
//        点击切换碎片
        vb.lawyerInfoTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    val fragment = when (tab.position) {
                        0 -> serviceModeFragment
                        1 -> userEvaluationFragment
                        else -> serviceModeFragment
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.lawyer_info_container, fragment).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun loadContent(id: Int) {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/${id}", "GET", null, true) {
                val data = g.fromJson(it, LawyerInfoBean::class.java).data
                vb.lawyerInfoName.text = "名字:${data.name}"
                vb.lawyerInfoServiceTimes.text = "服务次数:${data.serviceTimes}"
                vb.lawyerInfoFavorableRate.text = "咨询人数:${data.favorableRate}"
                vb.lawyerInfoLegalExpertiseName.text = "法律专长:${data.legalExpertiseName}"
                vb.lawyerInfoBaseInfo.text = "介绍:${data.baseInfo}"
                Glide.with(context).load(getUrl(data.avatarUrl)).into(vb.lawyerInfoAvatarUrl)
            }
        }
    }
}