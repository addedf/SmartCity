package com.example.smartcity.dialog

import android.app.ActionBar
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.smartcity.databinding.CustomDialogBinding

class CustomDialog(context: Context) : Dialog(context) {
    lateinit var vb : CustomDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = CustomDialogBinding.inflate(layoutInflater)
//        请求窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(vb.root)

        // 设置Dialog的宽度和高度
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params

        // 设置表单的监听和处理
        vb.dialogOK.setOnClickListener {
//            写入ip地址 算了反正进去了默认写入 自己写还容易写错 有个弹窗得了
            dismiss()
        }
//        取消关闭弹窗
        vb.dialogCancel.setOnClickListener {
            dismiss() // 关闭Dialog
        }
    }
}