package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityEditCurriculumVitaeBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class EditCurriculumVitaeActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityEditCurriculumVitaeBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.editCurrVitaeTb.setOnClickListener {
            finish()
        }
        vb.editCurrVitaeBtn.setOnClickListener {
            val data = """
                {
                "id": 1,
                "mostEducation": "${vb.editCurrVitaeMostEducation.text}",
                "education": "${vb.editCurrVitaeEducation.text}",
                "address": "${vb.editCurrVitaeAddress.text}",
                "experience": "${vb.editCurrVitaeExperience.text}",
                "individualResume": "${vb.editCurrVitaeIndividualResume.text};",
                "money": "${vb.editCurrVitaeMoney.text}",
                "positionId": 2
                }
            """.trimIndent()
            val body = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/job/resume", "PUT", body, true) {
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