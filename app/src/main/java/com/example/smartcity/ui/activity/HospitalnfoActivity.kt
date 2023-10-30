package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.*
import com.example.smartcity.bean.HospitalBanner
import com.example.smartcity.bean.HospitalIfoBean
import com.example.smartcity.databinding.ActivityHospitalnfoBinding
import com.example.smartcity.databinding.ActivityPetHospitalnfoBinding

class HospitalnfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityHospitalnfoBinding::inflate)
//    全局变量延迟赋值
    private val hospital : Int by lazy {
        intent.getIntExtra("id",0)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        intent.getIntExtra("id",0)
        vb.hospitalInfoBtn.setOnClickListener {
            jump(RegistrationActivity::class.java)
        }
        loadBanner()
        loadInfo()
    }

    private fun loadInfo() {
        tool.apply {
            send("/prod-api/api/hospital/hospital/${hospital}","GET",null,true) {
                val data = g.fromJson(it,HospitalIfoBean::class.java).data
                vb.hospitalInfoHospitalName.text = data.hospitalName
                vb.hospitalInfoLevel.text = data.level.toString()
                vb.hospitalInfoBrief.text = data.brief
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/hospital/banner/list?hospitalId=1","GET",null,true) {
                val data = g.fromJson(it, HospitalBanner::class.java).data
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].imgUrl)
                }
                setBanner(vb.hospitalInfoBanner,list)
            }
        }
    }
}