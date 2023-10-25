package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerServiceBean
import com.example.smartcity.databinding.ActivityLawyerGoodBinding
import com.example.smartcity.databinding.ItemGovServiceBinding
import java.lang.Exception

class LawyerGoodActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityLawyerGoodBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.layGoodTb.setOnClickListener {
            finish()
        }
        tool.apply {
            send("/prod-api/api/lawyer-consultation/legal-expertise/list", "GET", null, true) {
                val data = g.fromJson(it, LawyerServiceBean::class.java).rows.sortedBy { it.sort }
                vb.layGoodList.adapter = GenericAdapter(data.size,
                    { ItemGovServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.setOnClickListener {

                    }
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        500,
                        250
                    )
                    binding.itemGovName.text = data[position].name
                    Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemGovImg)
                }
                vb.layGoodList.layoutManager = GridLayoutManager(context, 4,GridLayoutManager.VERTICAL,true)
            }
        }
    }
}