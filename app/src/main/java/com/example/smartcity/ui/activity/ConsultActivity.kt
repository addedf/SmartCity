package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.bean.LawyerInfoBean
import com.example.smartcity.databinding.ActivityConsultBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ConsultActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityConsultBinding::inflate)
    val TAG = "ConsultActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.consultTb.setOnClickListener {
            finish()
        }
        val id = intent.getIntExtra("id",0)
        val legalExpertiseId = intent.getIntExtra("legalExpertiseId",0)
        vb.consultBtn.setOnClickListener {
            val data = """
                {
                "lawyerId": $id,
                "legalExpertiseId": $legalExpertiseId,
                "content": "${vb.consultQuestion.text}",
                "imageUrls":"/dev-api/profile/upload/image/2022/02/25/19d10a51-2950-46b0-ad70-daf7c5160320.jpg,/dev-api/profile/upload/image/2022/02/25/7dd5505a-8ffb49d5-81e2-58a66f08d34a.png",
                "phone": "${vb.consultPhone.text}"
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/lawyer-consultation/legal-advice","POST",data,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"提交成功",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/${id}", "GET", null, true) {
                val data = g.fromJson(it, LawyerInfoBean::class.java).data
                vb.consultName.text = "名字:${data.name}"
                vb.consultServiceTimes.text = "服务次数:${data.serviceTimes}"
                vb.consultLegalExpertiseName.text = "咨询人数:${data.favorableRate}"
                vb.consultLegalExpertiseName.text = "法律专长:${data.legalExpertiseName}"
                vb.consultBaseInfo.text = "介绍:${data.baseInfo}"
                Glide.with(context).load(getUrl(data.avatarUrl)).into(vb.consultAvatarUrl)
            }
        }
    }
}