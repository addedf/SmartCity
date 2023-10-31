package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityRegAddUserBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class RegAddUserActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRegAddUserBinding::inflate)
    val TAG = "RegAddUserActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.regAddUserTb.setOnClickListener {
            finish()
        }
        val money = intent.getFloatExtra("money", 0F)
        val categoryId = intent.getIntExtra("categoryId",0)
        vb.regAddUserMoney.text = "${money}元"
        val name = vb.regAddUserName.text
        var reg = ""
        vb.regAddUserRg.setOnCheckedChangeListener{_,checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.isChecked) {
                reg = when(selectedRadioButton.text) {
                    "专家号" -> "1"
                    else -> "2"
                }

            }
        }
        var isChecked = true
        vb.regAddUserTime.setOnClickListener {
            if (isChecked) {
                isChecked = false
                vb.datePicker.visibility = View.VISIBLE
            } else {
                isChecked = true
                vb.datePicker.visibility = View.GONE
            }

        }
        var dateString = ""
        vb.datePicker.setOnDateChangedListener { _, year, monthOfYear, dayOfMonth ->
            val calendar = Calendar.getInstance()
            calendar.set(year, monthOfYear, dayOfMonth)

            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            dateString = dateFormat.format(calendar.time)
            vb.datePicker.visibility = View.GONE
        }
        vb.regAddUserBtn.setOnClickListener {
            val data = """
                {
                "categoryId": $categoryId,
                "money": $money,
                "patientName": "$name",
                "reserveTime": "$dateString",
                "type": $reg
                }
            """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/hospital","POST",req,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(context,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}