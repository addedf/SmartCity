package com.example.smartcity.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.ServiceBean
import com.example.smartcity.databinding.FragmentServiceBinding
import com.example.smartcity.databinding.ItemServiceBinding
import com.example.smartcity.jump
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.*
import com.google.gson.Gson

class ServiceFragment : Fragment() {
    lateinit var vb: FragmentServiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vb = FragmentServiceBinding.inflate(layoutInflater)
//        加载服务方法
        loadService()
        return vb.root
    }

    private fun loadService() {
        tool.apply {
            send("/prod-api/api/service/list", "GET", null, false) {
//                添加服务
//                val list = mutableListOf<ServiceBean>()
//                list.add()
                val bean = Gson().fromJson(it, ServiceBean::class.java)
                val adapter = GenericAdapter(bean.rows.size,
                    { ItemServiceBinding.inflate(layoutInflater) }) { binding, position ->
//                    点击跳转服务界面 GarbageSortingActivity 内存泄露文件 垃圾分类重写
                    binding.root.setOnClickListener {
                        when (bean.rows[position].serviceName) {
                            "宠物医院" -> jump(PetHospitalActivity::class.java)
                            "法律服务" -> jump(LawyerActivity::class.java)
                            "政府服务热线" -> jump(GovernmentActivity::class.java)
                            "垃圾分类" -> jump(RecActivity::class.java)
                            "活动管理" -> jump(EventsActivity::class.java)
                            "志愿服务" -> jump(VolunteerActivity::class.java)
                            "爱心捐赠" -> jump(WelfareActivity::class.java)
                            "青年驿站" -> jump(YouthActivity::class.java)
                            "生活缴费" -> jump(UtilityBillsActivity::class.java)
                            "找工作" -> jump(JobActivity::class.java)
                            "物流查询" -> jump(ExpressActivity::class.java)
                            "看电影" -> jump(MovieActivity::class.java)
                            "停哪儿" -> jump(StopCarActivity::class.java)
                            "数字图书馆" -> jump(LibraryActivity::class.java)
                            "找房子" -> jump(HouseActivity::class.java)
                            else -> Toast.makeText(context, "待开发", Toast.LENGTH_SHORT).show()
                        }
                    }
                    binding.itemServiceName.text = bean.rows[position].serviceName
                    Glide.with(context).load(getUrl(bean.rows[position].imgUrl)).centerCrop()
                        .into(binding.itemServiceIcon)
                }
                vb.serviceList.adapter = adapter
                vb.serviceList.layoutManager = GridLayoutManager(context, 5)
            }
        }
    }
}