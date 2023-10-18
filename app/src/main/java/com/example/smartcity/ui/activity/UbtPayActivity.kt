package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.widget.RadioButton
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityUbtPayBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import java.text.SimpleDateFormat
import java.util.*

class UbtPayActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityUbtPayBinding::inflate)
    var price = ""
    var phone = ""
    var operator = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.ubtpTb.setOnClickListener {
            finish()
        }
//        接收传递来的手机号和运营商
        phone = intent.getStringExtra("phone").toString()
        operator = intent.getStringExtra("operator").toString()
        vb.utbpPhone.text = phone
        vb.uptpRdg.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.isChecked) {
                price = selectedRadioButton.text.toString()
            }
        }
        vb.ubtBtn.setOnClickListener {
            if (price.isEmpty() || vb.utbpPhone.text.isEmpty()) {
                Toast.makeText(this, "输入信息不完整", Toast.LENGTH_SHORT).show()
            } else {
                insert()
                finish()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun insert() {
        tool.apply {
            if (get("ubt_phone_list").isEmpty()) {
//                写入手机号运营商和时间
                set(
                    "ubt_phone_list", "$phone@$operator@${
                        SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(
                            Date()
                        )
                    }"
                )
            } else {
//                不为空则 先判断是否存有这个手机号
                if (!get("ubt_phone_list").contains(phone)) {
                    val data = get("ubt_phone_list")
//                    更新充值记录
                    set(
                        "ubt_phone_list", "$data;$phone@$operator@${
                            SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(
                                Date()
                            )
                        }"
                    )
                }
            }
        }
    }
}