package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RegListBean
import com.example.smartcity.databinding.ActivityRegistrationBinding
import com.example.smartcity.databinding.ItemRegListBinding
import com.example.smartcity.dialog.RegDialog
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegistrationActivity : AppCompatActivity() , OnItemClickListener {
    private val vb by viewBinding(ActivityRegistrationBinding::inflate)
    val TAG = "RegistrationActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        setSupportActionBar(vb.RegTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/hospital/patient/list","GET",null,true) {
                val data = g.fromJson(it,RegListBean::class.java).rows
                vb.regList.adapter = GenericAdapter(data.size,
                    { ItemRegListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemRegAddress.text = "地址:" + data[position].address
                    binding.itemRegName.text = "姓名:" + data[position].name
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context,RegInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        intent.putExtra("address",data[position].address)
                        intent.putExtra("name",data[position].name)
                        intent.putExtra("cardId",data[position].cardId)
                        intent.putExtra("userId",data[position].userId)
                        intent.putExtra("sex",data[position].sex)
                        intent.putExtra("tel",data[position].tel)
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).error(getDrawable(R.drawable.chengshi))
                            .into(binding.itemRegImgUrl)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                    binding.itemRegSex.text = when(data[position].sex) {
                        "0" -> "性别:男"
                        else -> "性别:女"
                    }
                }
                vb.regList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reg_nav,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when(item.itemId) {
                R.id.reg_add -> {
                    RegDialog(context,this@RegistrationActivity).show()
                }
            }
        }
        return true
    }

    override fun onItemClick(id: Int) {
        TODO("Not yet implemented")
    }

    override fun onUserInfo(
        name: String,
        sex: String,
        userId: String,
        phone: String,
        address: String
    ) {
//        获取时间戳
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formattedDate = currentDateTime.format(formatter)
        val data = """
            {
            "address": "$address",
            "birthday": "$formattedDate",
            "cardId": "$userId",
            "name": "$name",
            "sex": "$sex",
            "tel": "$phone"
            }
        """.trimIndent()
        val req = data.toRequestBody("application/json".toMediaTypeOrNull())
        tool.apply {
            send("/prod-api/api/hospital/patient","POST",req,true) {
                if (it.contains("操作成功")) {
                    loadList()
                    Toast.makeText(context,"添加成功",Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context,JSONObject(it).getString("msg"),Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCarInfo(engineNo: String, plateNo: String, type: String) {
        TODO("Not yet implemented")
    }
}