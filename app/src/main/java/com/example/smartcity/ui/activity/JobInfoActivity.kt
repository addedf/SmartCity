package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.bean.JobInfoBean
import com.example.smartcity.databinding.ActivityJobInfoBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class JobInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityJobInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.jobInfoTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        tool.apply {
            send("/prod-api/api/job/post/$id","GET",null,false) {
                val data = g.fromJson(it,JobInfoBean::class.java).data
                vb.jobInfo2name.text = data.name?:"暂无"
                vb.jobInfoAddress.text = data.address?:"暂无"
                vb.jobInfoContacts.text = data.contacts?:"暂无"
                vb.jobInfoComName.text = data.companyName?:"暂无"
                vb.jobInfoNeed.text = data.need?:"暂无"
                vb.jobInfoSalary.text = data.salary?:"暂无"
                vb.jobInfoObligation.text = data.obligation?:"暂无"
                vb.jobInfoName.text = data.professionName?:"暂无"
                vb.jobInfoComInfo.text = "暂无"
                vb.jobInfoBtn.setOnClickListener{
                    Toast.makeText(context,"投递成功", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}