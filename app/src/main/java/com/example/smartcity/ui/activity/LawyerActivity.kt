package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerBean
import com.example.smartcity.bean.LawyerHotListBean
import com.example.smartcity.bean.LawyerServiceBean
import com.example.smartcity.databinding.ActivityLawyerBinding
import com.example.smartcity.databinding.ItemGovServiceBinding
import com.example.smartcity.databinding.ItemHotLawyerListBinding
import java.lang.Exception

class LawyerActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityLawyerBinding::inflate)
    val TAG = "LawyerActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.lawyerTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
                loadBanner()
                loadService()
                loadHotLawyer()
                vb.layMeBtn.setOnClickListener {
                    tool.apply {
                        jump(MeConsultActivity::class.java)
                    }
                }
            } else {
                tool.snackbar(vb.root,"未登录","去登录") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    private fun loadHotLawyer() {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/list-top10","GET",null,true) {
                val data = g.fromJson(it,LawyerHotListBean::class.java).data.sortedBy { it.favorableRate }
                vb.lawHotList.adapter = GenericAdapter(data.size,
                    { ItemHotLawyerListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context,LawyerInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.itemLawyerBaseInfo.text = "介绍:${data[position].baseInfo}"
                    binding.itemLawyerName.text = data[position].name
                    binding.itemLawyerLegalExpertiseId.text = "从业时长:${data[position].legalExpertiseId}年"
                    binding.itemLawyerLegalExpertiseName.text = "法律专长:${data[position].legalExpertiseName}"
                    Glide.with(context).load(getUrl(data[position].avatarUrl)).into(binding.itemLawyerAvatarUrl)
                }
                vb.lawHotList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadService() {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/legal-expertise/list","GET",null,true) {
                val data = g.fromJson(it,LawyerServiceBean::class.java).rows.sortedBy { it.sort }
                vb.lawService.adapter = GenericAdapter(data.size,
                    { ItemGovServiceBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.root.setOnClickListener {
                        jump(LawyerGoodActivity::class.java)
                    }
                    binding.itemGovName.text = data[position].name
                    binding.root.layoutParams = LinearLayout.LayoutParams(280, 210)
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemGovImg)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.lawService.layoutManager = GridLayoutManager(context,2,GridLayoutManager.HORIZONTAL,false)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/ad-banner/list","GET",null,true) {
                val data = g.fromJson(it,LawyerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.lawBanner,list)
            }
        }
    }
}