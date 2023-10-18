package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.smartcity.R
import com.example.smartcity.bean.CurriculumVitaeBean
import com.example.smartcity.databinding.ActivityCurriculumVitaeBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class CurriculumVitaeActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityCurriculumVitaeBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.currVitaeTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/job/resume/queryResumeByUserId/1","GET",null,true) {
                val data = g.fromJson(it,CurriculumVitaeBean::class.java).data
                vb.currVitaeEducation.text = data.education
                vb.currVitaeMostEducation.text = data.mostEducation
                vb.currVitaeAddress.text = data.address
                vb.currVitaeExperience.text = data.experience?:"暂无"
                vb.currVitaeIndividualResume.text = data.individualResume
                vb.currVitaeMoney.text = data.money
            }
        }
    }
}