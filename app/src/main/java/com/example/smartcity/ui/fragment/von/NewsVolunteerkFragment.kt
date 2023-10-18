package com.example.smartcity.ui.fragment.von

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.VohListBean
import com.example.smartcity.bean.VolunteerBannerBean
import com.example.smartcity.databinding.FragmentNewsVolunteerkBinding
import com.example.smartcity.databinding.ItemVonListBinding
import com.example.smartcity.ui.activity.VolunteerkNewsActivity

class NewsVolunteerkFragment : Fragment() {
    private val vb by viewBinding(FragmentNewsVolunteerkBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadBanner()
        loadNewsList()
        return vb.root
    }

    private fun loadNewsList() {
        tool.apply {
            send("/prod-api/api/volunteer-service/news/list", "GET", null, true) {
                val data = g.fromJson(it, VohListBean::class.java).rows.sortedBy { it.createTime }
                vb.vohList.adapter = GenericAdapter(data.size,
                    { ItemVonListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = RecyclerView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context, VolunteerkNewsActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.vohItemTitle.text = data[position].title
                    binding.vohItemTime.text = data[position].createTime
                    binding.vohItemSummary.text = data[position].summary
                    Glide.with(context).load(getUrl(data[position].imgUrl))
                        .transform(CenterCrop(),RoundedCorners(5.dp))
                        .into(binding.vohItemCover)
                }
                vb.vohList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/volunteer-service/ad-banner/list", "GET", null, true) {
                val data = g.fromJson(it, VolunteerBannerBean::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.vohBanner, list)
            }
        }
    }
}