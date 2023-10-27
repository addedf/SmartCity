package com.example.smartcity.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerServiceBean
import com.example.smartcity.databinding.ItemGovServiceBinding
import com.example.smartcity.databinding.ScreenDialogBinding
import java.lang.Exception

//接受使用接口的上下文
class ScreenDialog(context:Context, private val listener: OnItemClickListener) : Dialog(context) {
//    弹窗只能使用这样绑定id的方式
    lateinit var vb : ScreenDialogBinding
    val TAG = "ScreenDialog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ScreenDialogBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(vb.root)
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
        vb.screenDialogErr.setOnClickListener {
            dismiss()
        }
        vb.screenDialogOk.setOnClickListener {
            dismiss()
        }
        tool.apply {
            send("/prod-api/api/lawyer-consultation/legal-expertise/list","GET",null,true) {
                val data = g.fromJson(it, LawyerServiceBean::class.java).rows.sortedBy { it.sort }
                vb.screenDialogList.adapter = GenericAdapter(data.size,
                    { ItemGovServiceBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.itemGovName.text = data[position].name
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        250
                    )
                    binding.root.setOnClickListener {
//                        使用接口
                        listener.onItemClick(data[position].id)
                        dismiss()
                    }
                    try {
                        Glide.with(context).load(getUrl(data[position].imgUrl)).into(binding.itemGovImg)
                    } catch (e: Exception) {
                        Log.e(TAG, "${e.message}")
                    }
                }
                vb.screenDialogList.layoutManager = GridLayoutManager(context, 4)
            }
        }
    }
}