package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityTakePayBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class TakePayActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakePayBinding::inflate)
    val TAG = "TakePayActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val orderNo = intent.getStringExtra("orderNo")
        val total = intent.getDoubleExtra("total",0.0)
        vb.takePayText.text = total.toString()
        val data = """
            {
            "orderNo": "$orderNo",
            "paymentType":"电子支付"
            }
        """.trimIndent()
        val req = data.toRequestBody("application/json".toMediaTypeOrNull())
        vb.takePayBtn.setOnClickListener {
            tool.apply {
                send("/prod-api/api/takeout/pay","POST",data,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"支付成功",Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context,JSONObject(it).toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}