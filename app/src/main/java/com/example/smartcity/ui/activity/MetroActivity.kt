package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.BannerBean
import com.example.smartcity.bean.MetroHomeListBean
import com.example.smartcity.databinding.ActivityMetroBinding
import com.example.smartcity.databinding.ItemMetroListBinding

class MetroActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityMetroBinding::inflate)
    val TAG = "MetroActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.metroTb.setOnClickListener {
            finish()
        }
        vb.metroAtlas.setOnClickListener {
            jump(MetroAtlasActivity::class.java)
        }
        setSupportActionBar(vb.metroTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        loadBanner()
        loadList()
    }
    //    添加菜单栏内容
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.metro_all,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        tool.apply {
            when(item.itemId) {
                R.id.metro_all -> jump(MetroAllActivity::class.java)
            }
        }
        return true
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/metro/list?currentName=建国门","GET",null,false) {
                val data = g.fromJson(it, MetroHomeListBean::class.java).data
                vb.metroList.adapter = GenericAdapter(data.size,
                    { ItemMetroListBinding.inflate(layoutInflater) }) { binding,position->
                    binding.itemMetroListCurrentName.text = "当前站点：" + data[position].currentName
                    binding.itemMetroListLineName.text = data[position].lineName
                    binding.itemMetroListNextName.text = "下一站" + data[position].nextStep.name
                    binding.itemMetroListReachTime.text = "间隔时间:" + data[position].reachTime.toString()+ "分钟"
                    binding.root.setOnClickListener {
                        val intent = Intent(context,MetroInfoActivity::class.java)
                        intent.putExtra("id",data[position].lineId)
                        startActivity(intent)
                    }
                }
                vb.metroList.layoutManager = LinearLayoutManager(context)
            }
        }
    }

    private fun loadBanner() {
        tool.apply {
            send("/prod-api/api/metro/rotation/list","GET",null,false) {
                val data = g.fromJson(it,BannerBean::class.java).rows
                val list = mutableListOf<String>()
                for (i in data.indices) {
                    list.add(data[i].advImg)
                }
                setBanner(vb.metroBanner,list)
            }
        }
    }
}