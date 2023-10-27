package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.bean.MeConsultInfoBean
import com.example.smartcity.databinding.ActivityMeConsultInfoBinding

class MeConsultInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMeConsultInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.meConsultInfoTb.setOnClickListener { finish() }
        val id = intent.getIntExtra("id",0)
        vb.meConsultInfoBtn.setOnClickListener {
            jump(EvaluateActivity::class.java)
        }
        tool.apply {
            send("/prod-api/api/lawyer-consultation/legal-advice/${id}","GET",null,true) {
                val data = g.fromJson(it,MeConsultInfoBean::class.java).data
                Glide.with(context).load(getUrl(data.imageUrls))
                    .error(getDrawable(R.drawable.chengshi))
                    .into(vb.meConsultInfoImageUrls)
                vb.meConsultInfoContent.text = data.content
                vb.meConsultInfoCreateTime.text = "创建时间:" + data.createTime
                vb.meConsultInfoLawyerName.text = data.lawyerName
                vb.meConsultInfoLegalExpertiseName.text = data.legalExpertiseName
                vb.meConsultInfoPhone.text = data.phone
                vb.meConsultInfoState.text =  when(data.state) {
                    "0" -> "未解决"
                    else -> "已解决"
                }
                vb.meConsultInfoFromUserId.text = "点赞数量:" + data.fromUserId
            }
        }
    }
}