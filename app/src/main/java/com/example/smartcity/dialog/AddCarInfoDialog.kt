package com.example.smartcity.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.smartcity.OnItemClickListener
import com.example.smartcity.databinding.ItemAddCarInfoDialogBinding

class AddCarInfoDialog(context: Context,private val listener: OnItemClickListener) : Dialog(context) {
    lateinit var vb : ItemAddCarInfoDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ItemAddCarInfoDialogBinding.inflate(layoutInflater)
//        请求窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(vb.root)

        // 设置Dialog的宽度和高度
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params

        vb.addCarErr.setOnClickListener {
            dismiss()
        }
        vb.addCarOk.setOnClickListener {
            val engineNo = vb.addCarEngineNo.text.toString()
            val plateNo = vb.addCarPlateNo.text.toString()
            val type = vb.addCarType.text.toString()
            listener.onCarInfo(engineNo,plateNo,type)
            dismiss()
        }
    }
}