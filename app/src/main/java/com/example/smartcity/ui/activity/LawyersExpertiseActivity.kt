package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.R
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerHotListBean
import com.example.smartcity.bean.LawyerScreenBean
import com.example.smartcity.databinding.ActivityLawyersExpertiseBinding
import com.example.smartcity.databinding.ItemHotLawyerListBinding
import com.example.smartcity.dialog.ScreenDialog
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import kotlin.properties.Delegates

class LawyersExpertiseActivity : AppCompatActivity() {
    private var id = 0
    val TAG = "LawyersExpertiseActivity"
    private val vb by viewBinding(ActivityLawyersExpertiseBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.lawExperIc.setOnClickListener {
            finish()
        }
        id = intent.getIntExtra("id",0)
//        loadList()
//        设置右上角按钮
        setSupportActionBar(vb.lawExperTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
//        TODO("暂不id类别排序，律师太少无法体现排序效果")
        loadList(id,"workStartAt")
    }

    private fun loadList(id: Int,sort:String) {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/lawyer/list?sort=${sort}","GET",null,true) {
                val data = g.fromJson(it, LawyerScreenBean::class.java).rows
                vb.lawExperList.adapter = GenericAdapter(data.size,
                    { ItemHotLawyerListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context,LawyerInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    binding.itemLawyerBaseInfo.text = "介绍:${data[position].baseInfo}"
                    binding.itemLawyerName.text = data[position].name
                    binding.itemLawyerLegalExpertiseId.text = "从业时长:${data[position].legalExpertiseId}年"
                    binding.itemLawyerLegalExpertiseName.text = "法律专长:${data[position].legalExpertiseName}"
                    Glide.with(context).load(getUrl(data[position].avatarUrl)).into(binding.itemLawyerAvatarUrl)
                }
                vb.lawExperList.layoutManager = object :LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.lawyers_expertise,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.law_exper_favorableRate -> {
                loadList(id,"workStartAt")
            }
            R.id.law_exper_serviceTimes -> {
                loadList(id,"serviceTimes")
            }
            R.id.law_exper_workStartAt -> {
                loadList(id,"favorableRate")
            }
        }
        return true
    }
}