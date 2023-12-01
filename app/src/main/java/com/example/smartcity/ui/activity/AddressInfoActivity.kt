package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityAddressInfoBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AddressInfoActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityAddressInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.addressInfoTb.setOnClickListener {
            finish()
        }
        tool.apply {
            vb.addressInfoBtn.setOnClickListener {
                val data = """
                {
                "addressDetail": "${vb.addressInfoAddressDetail.text}",
                "label": "${vb.addressInfoLabel.text}",
                "name": "${vb.addressInfoName.text}",
                "phone": "${vb.addressInfoPhone.text}"
                }
            """.trimIndent()
                val req = data.toRequestBody("application/json".toMediaTypeOrNull())
                send("/prod-api/api/takeout/address","POST",data,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(this@AddressInfoActivity,"添加成功",Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@AddressInfoActivity,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}