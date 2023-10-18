package com.example.smartcity.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.YouthAreaListBean
import com.example.smartcity.bean.YouthInnListBean
import com.example.smartcity.databinding.ActivityYouthBinding
import com.example.smartcity.databinding.ItemYaCreaBinding
import com.example.smartcity.databinding.ItemYaYiBinding
import java.lang.Exception

class YouthActivity : AppCompatActivity() {
    private val vb by viewBinding(ActivityYouthBinding::inflate)
    val TAG = "YouthActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.yaTb.setOnClickListener {
            finish()
        }
        tool.checkToken {
            if (it) {
//                政策宣传
                loadAreaList()
//                驿站列表
                loadYiList()
            } else {
                tool.snackbar(vb.root,"未登录","去登录"){
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun loadYiList() {
        tool.apply {
            send("/prod-api/api/youth-inn/youth-inn/list","GET",null,true) {
                val data = g.fromJson(it, YouthInnListBean::class.java).rows
                vb.yaYiList.adapter = GenericAdapter(data.size,
                    { ItemYaYiBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    binding.itemYaYiName.text = data[position].name
                    binding.itemYaYiAddress.text = data[position].address
                    binding.itemYaYiBed.text = "剩余床位: 男(${data[position].bedsCountBoy}) 女(${data[position].bedsCountGirl})"
                    binding.itemYaYiIntroduce.text = data[position].introduce
//                    点击介绍介绍增加最大长度
                    binding.itemYaYiIntroduce.setOnClickListener {
                            it1 ->
                        if((it1 as TextView).maxLines == 2){
                            it1.maxLines = Int.MAX_VALUE
                        }else{
                            it1.maxLines = 2
                        }
                    }
                    Glide.with(context).load(getUrl(data[position].coverImgUrl)).transform(
                        CenterCrop(),
                        RoundedCorners(5.dp)
                    ).into(binding.itemYaYiCover)
                    binding.root.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                    点击跳转 详情
                    binding.root.setOnClickListener {
                        val intent = Intent(context,YiInfoActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.yaYiList.layoutManager = object: LinearLayoutManager(context){
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    private fun loadAreaList() {
        tool.apply {
            send("/prod-api/api/youth-inn/talent-policy-area/list","GET",null,true) {
                val data = g.fromJson(it,YouthAreaListBean::class.java).data
                vb.yaAreaList.adapter = GenericAdapter(data.size,
                    { ItemYaCreaBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemYaAreaName.text = data[position].name
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).transform(
                            CenterCrop(),
                            RoundedCorners(5.dp)
                        ).into(binding.itemYaAreaImage)
                    } catch (e:Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                    binding.root.setOnClickListener {
                        val intent = Intent(context,YipActivity::class.java)
                        intent.putExtra("id",data[position].id)
                        startActivity(intent)
                    }
                }
                vb.yaAreaList.layoutManager = GridLayoutManager(context,3)
            }
        }
    }
}