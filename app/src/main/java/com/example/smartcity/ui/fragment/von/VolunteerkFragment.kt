package com.example.smartcity.ui.fragment.von

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.VohMainListBean
import com.example.smartcity.bean.VolunteerBannerBean
import com.example.smartcity.databinding.FragmentVolunteerkBinding
import com.example.smartcity.databinding.ItemVohMainListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.VoInfoActivity
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception

class VolunteerkFragment : Fragment() {
    private val vb by viewBinding(FragmentVolunteerkBinding::inflate)
    val TAG = "VolunteerkFragment"
    private var filter = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadList(filter)
//        搜索功能
        vb.vohMainSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    filter = query
                    loadList(filter)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = true

        })
        return vb.root
    }

    @SuppressLint("SetTextI18n")
    private fun loadList(filter: String) {
        tool.apply {
            send("/prod-api/api/volunteer-service/activity/list", "GET", null, true) {
                val data = g.fromJson(it, VohMainListBean::class.java).rows
                val list = mutableListOf<VohMainListBean.Data>()
                for (d in data) {
//                    根据输入的条件add入数据
                    if (d.title.contains(filter) || d.requireText.contains(filter) || d.undertaker.contains(
                            filter
                        )
                    ) {
                        list.add(d)
                    }
                }
                vb.vonMainList.adapter = GenericAdapter(list.size,
                    { ItemVohMainListBinding.inflate(layoutInflater) }) { binding, position ->
                    try {
                        binding.root.layoutParams = RecyclerView.LayoutParams(
                            RecyclerView.LayoutParams.MATCH_PARENT,
                            RecyclerView.LayoutParams.WRAP_CONTENT
                        )
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
//                    点击跳转详细活动
                    binding.root.setOnClickListener {
                        val intent = Intent(context, VoInfoActivity::class.java)
                        intent.putExtra("id",list[position].id)
                        startActivity(intent)
                    }
                    binding.vohMainItemTitle.text = list[position].title
                    binding.vohMainItemTime.text = "开始时间:${list[position].startAt}"
                    binding.vohMainItemUndertaker.text = "发布者:${list[position].undertaker}"
                    binding.vohMainItemRequireText.text = "要求:${list[position].requireText}"
//                    点击报名
                    binding.vohMainItemSubmit.setOnClickListener {
//                        val data = """
//                            "activityId": ${list[position].id},
//                            "newState": true
//                        """.trimIndent()
//                        val req = data.toRequestBody("application/json".toMediaTypeOrNull())
//                        val reqBody = RequestBody.create("application/json".toMediaTypeOrNull(),"""
//                            {
//                            "activityId": ${list[position].id},
//                            "newState": true
//                            }
//                        """.trimIndent())
                        val data = """
                            {
                            "activityId": ${list[position].id},
                            "newState": true
                            }
                        """.trimIndent()
//                        提交请求
                        tool.apply {
                            send(
                                "/prod-api/api/volunteer-service/register",
                                "POST",
                                data,
                                true
                            ) { it1 ->
//                                区分开两个不同的it 一个是本作用域的返回值，一个是上一个作用域的是否能报名的状态
                                if (it1.contains("操作成功")) {
                                    Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show()
                                    it.isEnabled = false
                                    (it as Button).text = "已报名"
                                } else {
                                    try {
                                        Toast.makeText(
                                            context,
                                            JSONObject(it1).getString("msg"),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } catch (_: Exception) {
                                        Toast.makeText(context, "操作失败", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        }
                    }
                }
                vb.vonMainList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}