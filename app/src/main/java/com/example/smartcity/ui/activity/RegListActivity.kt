package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.RegBean
import com.example.smartcity.databinding.ActivityRegListBinding
import com.example.smartcity.databinding.ItemRegBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.internal.wait
import java.lang.Exception

class RegListActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityRegListBinding::inflate)
    val TAG = "RegListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.RegListTb.setOnClickListener {
            finish()
        }
        loadList()
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/hospital/category/list","GET",null,true) {
                val data = g.fromJson(it,RegBean::class.java).rows
                vb.regList.adapter = GenericAdapter(data.size,
                    { ItemRegBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemRegTitle.text = "门诊: " + data[position].categoryName
                    binding.itemRegMoney.text = "挂号费: " + data[position].money
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context,RegAddlistActivity::class.java)
                        intent.putExtra("categoryName",data[position].categoryName)
                        intent.putExtra("money",data[position].money)
                        intent.putExtra("categoryId",data[position].categoryId)
                        startActivity(intent)
                    }
                }
                vb.regList.layoutManager = object : LinearLayoutManager(context){
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }
}