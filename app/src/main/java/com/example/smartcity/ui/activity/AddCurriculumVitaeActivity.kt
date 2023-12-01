package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityAddCurriculumVitaeBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddCurriculumVitaeActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityAddCurriculumVitaeBinding::inflate)
    val TAG = "AddCurriculumVitaeActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.addCurrVitaeTb.setOnClickListener {
            finish()
        }
//        新增简历变量  TODO("一个token只能新增一次")
        var positionId = 3
        vb.addCurrVitaeBtn.setOnClickListener {
            val data = """
                {
                "mostEducation": "${vb.addCurrVitaeMostEducation.text}",
                "education": "${vb.addCurrVitaeEducation.text}",
                "address": "${vb.addCurrVitaeAddress.text}",
                "experience": "${vb.addCurrVitaeExperience.text}",
                "individualResume": "${vb.addCurrVitaeIndividualResume.text};",
                "money": "${vb.addCurrVitaeMoney.text}",
                "positionId":"${positionId++}"
                }
            """.trimIndent()
            val body = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/job/resume", "POST", data, true) {
                    if (it.contains("操作成功")) {
                        finish()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }
}