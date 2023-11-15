package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.TakeOutListBean
import com.example.smartcity.bean.TakeOutTypeListBean
import com.example.smartcity.databinding.ActivityTakeOutListBinding
import com.example.smartcity.databinding.ItemTakeOutTypeListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class TakeOutListActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityTakeOutListBinding::inflate)
    val TAG = "TakeOutListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        val text = intent.getStringExtra("text")
        val id = intent.getIntExtra("id",0)
        vb.takeOutListTb.setOnClickListener {
            finish()
        }
        vb.takeOutListText.text = text
        loadList(id)
    }

    private fun loadList(id: Int) {
        tool.apply {
            send("/prod-api/api/takeout/seller/list?themeId=$id","GET",null,false) {
                val data = g.fromJson(it, TakeOutListBean::class.java).rows
                vb.takeOutTypeList.adapter = GenericAdapter(data.size,
                    { ItemTakeOutTypeListBinding.inflate(layoutInflater) }) { binding,position ->
                    Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemTakeOutTypeImgUrl)
                    binding.itemTakeOutTypeIntroduction.text = data[position].introduction
                    binding.itemTakeOutTypeName.text = data[position].name
                    binding.itemTakeOutTypeScore.text = "评分" + data[position].score.toString()
                    binding.root.setOnClickListener {
                        val intent = Intent(context,TakeOrderingFoodActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.takeOutTypeList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}