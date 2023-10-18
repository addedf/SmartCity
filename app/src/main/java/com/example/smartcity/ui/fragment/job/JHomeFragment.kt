package com.example.smartcity.ui.fragment.job

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.bean.JobListBean
import com.example.smartcity.databinding.FragmentJHomeBinding
import com.example.smartcity.databinding.ItemHotJobListBinding
import com.example.smartcity.databinding.ItemJobListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.JobInfoActivity
import com.example.smartcity.viewBinding
import java.lang.Exception

class JHomeFragment : Fragment() {
    val TAG = "JHomeFragment"
    var filter = ""
//    private val list = mutableListOf<JobListBean.Rows>()
    private val vb by viewBinding(FragmentJHomeBinding::inflate)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadBanner()
//        热门工作列表
        loadHotJobList()
//        工作列表
        loadList()
//        搜索功能
        vb.jobSearchView.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filter = query!!
                loadList()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true
        })
        return vb.root
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/job/post/list", "GET", null, true) {
                val data = g.fromJson(it, JobListBean::class.java).rows
                val list = mutableListOf<JobListBean.Rows>()
//                遍历数据搜索对比
                for (job in data) {
                    job.apply {
                        try {
//                            搜索的数据只要有一项是相同的就呈现
                            if (address.contains(filter) || professionName.contains(filter) || salary.contains(filter) || obligation.contains(filter)) {
                                list.add(job)
                            }
                        } catch (e: Exception) {
                            Log.e(TAG, "${e.message}")
                        }
                    }
                }
                val adapter = GenericAdapter(list.size,
                    { ItemJobListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.root.setOnClickListener {
                        val intent = Intent(context, JobInfoActivity::class.java)
                        intent.putExtra("id",list[position].id)
                        startActivity(intent)
                    }
                    binding.root.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    binding.itemJobAddress.text = list[position].address?:"暂无"
                    binding.itemJobName.text = list[position].professionName?:"暂无"
                    binding.itemJobObligation.text = list[position].obligation?:"暂无"
                    binding.itemJobSalary.text = list[position].salary?:"暂无"
                }
                vb.jobList.adapter = adapter
                val layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
                vb.jobList.layoutManager = layoutManager
            }
        }
    }

    private fun loadHotJobList() {
        tool.apply {
            send("/prod-api/api/job/profession/list", "GET", null, true) {
                val data = g.fromJson(it, JobListBean::class.java).rows
                // 创建一个映射，用于存储职位名称和它们的出现次数
                val professionCountMap = mutableMapOf<String, Int>()

                // 计算每个职位名称的出现次数
                for (job in data) {
                    val professionName = job.professionName
                    val count = professionCountMap.getOrDefault(professionName, 0)
                    professionCountMap[professionName] = count + 1
                }

                // 将映射按照值（出现次数）降序排序
                val sortedProfessions = professionCountMap.entries.sortedByDescending { it.value }

                // 创建一个用于存储职位名称的列表
                val sortedProfessionNames = mutableListOf<String>()

                // 遍历排序后的映射，将职位名称添加到列表中，但只添加一次，不重复
                val addedProfessionNames = mutableSetOf<String>()
                for (entry in sortedProfessions) {
                    val professionName = entry.key
                    if (!addedProfessionNames.contains(professionName)) {
                        sortedProfessionNames.add(professionName)
                        addedProfessionNames.add(professionName)
                    }
                }
                vb.jobHotList.adapter = GenericAdapter(3,
                    { ItemHotJobListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemHotBtn.text = sortedProfessionNames[position]
//                    点击传入职位
                    binding.root.setOnClickListener {
                        val list = mutableListOf<JobListBean.Rows>()
                        for (job in data) {
                            job.apply {
                                try {
//                            搜索的数据只要有一项是相同的就呈现
                                    if (address.contains(filter) || professionName.contains(filter) || salary.contains(filter) || obligation.contains(filter)) {
                                        list.add(job)
                                    }
                                } catch (e: Exception) {
                                    Log.e(TAG, "${e.message}")
                                }
                            }
                        }
                        filter = sortedProfessionNames[position]
                        loadList()
                    }
                }
                vb.jobHotList.layoutManager = GridLayoutManager(context, 3)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", "GET", null, false) {
                val data = g.fromJson(it, BannerBean::class.java).rows
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].advImg)
                }
                setBanner(vb.jobBanner, list)
            }
        }
    }
}