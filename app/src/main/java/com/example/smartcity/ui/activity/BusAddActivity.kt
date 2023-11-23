package com.example.smartcity.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.bean.BusAddOkBean
import com.example.smartcity.databinding.ActivityBusAddBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class BusAddActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityBusAddBinding::inflate)
    val TAG = "BusAddActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.busAddTb.setOnClickListener {
            finish()
        }
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        vb.busAddSubmit.setOnClickListener {
            val data = """
                    {
"start":"${vb.busAddStart.text}",
"end":"${vb.busAddEnd.text}",
"price":"${vb.busAddPrice.text}",
"path":"${vb.busAddPath.text}",
"status":1
}
            """.trimIndent()
            Log.e(TAG, "onCreate: $data", )
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/bus/order","POST",req,true) {
                    val data = g.fromJson(it,BusAddOkBean::class.java)
                    if (it.contains("操作成功")) {
//                        收起软键盘
                        imm.hideSoftInputFromWindow(vb.busAddSubmit.windowToken, 0)
                        snackbar(vb.root,"您的订单号:${data.orderNum}","去支付") {
                            startActivity(Intent(context,BusMeActivity::class.java))
                        }
                    } else{
                        Toast.makeText(context,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}