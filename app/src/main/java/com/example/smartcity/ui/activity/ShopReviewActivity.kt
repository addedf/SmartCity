package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityShopReviewBinding
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class ShopReviewActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityShopReviewBinding::inflate)
    val TAG ="ShopReviewActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.shopReviewTb.setOnClickListener {
            finish()
        }
        val orderNo = intent.getStringExtra("orderNo")
        vb.shopReviewBtn.setOnClickListener {
            val data = """
            {
            "content": "${vb.shopReviewInpnt.text}",
            "orderNo": "$orderNo",
            "score": 5
            }
        """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/takeout/comment","POST",data,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"评论成功",Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(context,JSONObject(it).toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}