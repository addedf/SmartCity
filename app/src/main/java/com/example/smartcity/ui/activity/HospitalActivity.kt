package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.bean.HospitalBanner
import com.example.smartcity.bean.HospitalIfoBean
import com.example.smartcity.bean.HospitalListBean
import com.example.smartcity.databinding.ActivityHospitalBinding
import com.example.smartcity.databinding.ItemHospitalListBinding
import java.lang.Exception

class HospitalActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHospitalBinding::inflate)
    val TAG = "HospitalActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        tool.checkToken {
            if (it) {
                loadBanner()
                loadList()
            } else {
                tool.snackbar(vb.root,"未登录","去登陆") {
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }
    private fun loadList() {
        tool.apply {
            send("/prod-api/api/hospital/hospital/list","GET",null,false) {
                val data = g.fromJson(it,HospitalListBean::class.java).rows
                vb.hospitalList.adapter = GenericAdapter(data.size,
                    { ItemHospitalListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemHospitalHospitalName.text = data[position].hospitalName
                    binding.itemHospitalLevel.text = "星级:${data[position].level}"
                    binding.itemHospitalBrief.text = "介绍:${data[position].brief}"
                    binding.root.setOnClickListener {
                        val intent = Intent(context,HospitalnfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).error(getDrawable(R.drawable.chengshi))
                            .transform(CenterCrop(),RoundedCorners(5.dp))
                            .into(binding.itemHospitalImgUrl)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.hospitalList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/hospital/banner/list?hospitalId=1","GET",null,true) {
                val data = g.fromJson(it,HospitalBanner::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.hospitalBanner,list)
            }
        }
    }
}