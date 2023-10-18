package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.databinding.ActivityUtilityBillsBinding
import com.example.smartcity.databinding.ItemUbtListBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class UtilityBillsActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityUtilityBillsBinding::inflate)
    val TAG = "UtilityBillsActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.ubtTb.setOnClickListener {
            finish()
        }
//        运营商
        var operator = ""
        vb.ubtRg.setOnCheckedChangeListener { _, checkedId ->
//            声明一个空的RadioButton对象用来接收选中的id
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.isChecked) {
                // 当前选中的RadioButton
                operator = selectedRadioButton.text.toString()
            }
        }
        vb.ubtBtn.setOnClickListener {
            if (operator.isEmpty() || vb.utbPhone.text.isEmpty()) {
                Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show()
            } else {
                goPay(operator, vb.utbPhone.text.toString())
            }
        }
        loadData()
    }

    private fun goPay(operator: String, phone: String) {
        val intent = Intent(this, UbtPayActivity::class.java)
        intent.putExtra("phone", phone)
        intent.putExtra("operator", operator)
        startActivity(intent)
    }
//    后台返回前台时再加载一次 充值记录
    override fun onResume() {
        loadData()
        super.onResume()
    }
    @SuppressLint("SetTextI18n")
    private fun loadData() {
//        读取本地充值记录
        val data = tool.get("ubt_phone_list")
        if (data.isNotEmpty()) {
            val phoneList = data.split(";")
            val adapter = GenericAdapter(phoneList.size,
                { ItemUbtListBinding.inflate(layoutInflater) }) { binding, position ->
                binding.root.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                val item = phoneList[position].split("@")
//                phoneList中存储的信息是数组的结构
                binding.itemUbtPhone.text = item[0]
                binding.itemUbtOperator.text = item[1]
                binding.itemUbtTime.text = "添加时间:${item[2]}"
//                不同运营商不同颜色
                binding.itemUbtOperator.setTextColor(
                    when (item[1]) {
                        "中国移动" -> Color.rgb(64, 224, 208)
                        "中国联通" -> Color.rgb(255, 69, 0)
                        "中国电信" -> Color.rgb(30, 144, 255)
                        else -> Color.BLACK
                    }
                )
//                点击充值记录带参数跳转
                binding.root.setOnClickListener{
                    goPay(item[1],item[0])
                }
            }
            vb.ubtList.adapter = adapter
            vb.ubtList.layoutManager = LinearLayoutManager(this)
        }
    }
}