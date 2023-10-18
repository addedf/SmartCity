package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.SearchContenBean
import com.example.smartcity.databinding.ActivitySearchContentBinding
import com.example.smartcity.databinding.ItemSearchContextBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import java.lang.Exception

class SearchContentActivity : AppCompatActivity() {
    lateinit var vb: ActivitySearchContentBinding
    val TAG = "SearchContentActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivitySearchContentBinding.inflate(layoutInflater)
        setContentView(vb.root)
        vb.searchTb.setOnClickListener {
            finish()
        }
        val OddNumber = intent.getStringExtra("OddNumber")
        tool.apply {
            send("/prod-api/api/logistics-inquiry/logistics_info/${OddNumber}", "GET", null, true) {
                val data = g.fromJson(it, SearchContenBean::class.java).data
                try {
                    vb.searchName.text = data.company.name
                    vb.searchOddNumber.text = data.infoNo
                    Glide.with(context).load(getUrl(data.company.imgUrl)).centerCrop()
                        .into(vb.searchIcon)
                    val adapter = GenericAdapter(data.stepList.size,
                        { ItemSearchContextBinding.inflate(layoutInflater) }) { binding, position ->
                        binding.itemSearchAddressAt.text = data.stepList[position].addressAt
                        binding.itemSearchEventAt.text = data.stepList[position].eventAt
                        binding.itemSearchStateName.text = data.stepList[position].stateName
                    }
                    vb.searchRec.adapter = adapter
                    vb.searchRec.layoutManager = LinearLayoutManager(context)
                } catch (e: Exception) {
                    Toast.makeText(context, "单号错误", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}