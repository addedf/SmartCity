package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.ConsultListBean
import com.example.smartcity.databinding.ActivityMeConsultBinding
import com.example.smartcity.databinding.ItemConsultListBinding
import java.lang.Exception

class MeConsultActivity : AppCompatActivity() {
    val TAG = "MeConsultActivity"
    private val vb by viewBinding(ActivityMeConsultBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.meConsultTb.setOnClickListener {
            finish()
        }
        setSupportActionBar(vb.meConsultTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        loadEvalue()
    }

    private fun loadEvalue() {
        tool.apply {
            send("/prod-api/api/lawyer-consultation/legal-advice/list?pageNum=1&pageSize=10&state=0","GET",null,true) {
                val data = g.fromJson(it,ConsultListBean::class.java).rows
                vb.meConsultList.adapter = GenericAdapter(data.size,
                    { ItemConsultListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemConsultListCreateTime.text = data[position].createTime
                    binding.itemConsultListLawyerName.text = data[position].lawyerName
                    binding.itemConsultListLegalExpertiseName.text = data[position].legalExpertiseName
                    binding.itemConsultListState.text = when(data[position].state) {
                        "0" -> "未解决"
                        else -> "已解决"
                    }
                    binding.root.setOnClickListener {
                        val intent = Intent(context,MeConsultInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].imageUrls)).error(getDrawable(R.drawable.chengshi))
                            .into(binding.itemConsultListImageUrls)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.meConsultList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.me_consult,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when(item.itemId) {
                R.id.me_consult_add -> jump(ConsultListActivity::class.java)
            }
        }
        return true
    }
}