package com.example.smartcity.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.WindowManager
import com.example.smartcity.databinding.ActivityInspectCarBinding
import com.example.smartcity.databinding.DialogInspectCarBinding
import com.example.smartcity.databinding.ScreenDialogBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding

class InspectCarDialog(context: Context) : Dialog(context) {
    val TAG = "InspectCarDialog"
    lateinit var vb : DialogInspectCarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = DialogInspectCarBinding.inflate(layoutInflater)
        setContentView(vb.root)
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        tool.apply {
            send("/prod-api/api/traffic/checkCar/grt","GET",null,true) {
                Log.e(TAG, "onCreate: ${get("token")}", )
                val str = "<p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;fontstyle:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;t\n" +
                        "ext-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">1.</span><span style=\\\\\\\"fo\n" +
                        "nt-size:16px;\\\\\\\">至少提前两周预约</span><span style=\\\\\\\"font-size:16px;\\\\\\\">，<span style=\\\\\\\"font-size:100%;\\\\\\\">预</span>约确定以支付定金为准，定金20 元(不可退)；拍照排期以收到定金顺序为准。\n" +
                        "</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:no\n" +
                        "ne;\\\\\\\"></span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-styl\n" +
                        "e:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;textindent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">2.检车余款于拍照当日结清：支付宝／微信／现金均可。\n" +
                        "</span></p><span style=\\\\\\\"background-color:#FFFFFF;color:#191919;font-family:&quot;font-size:16px;font-style:normal;font-weight:400;line-height:1.9;text-decoration:no\n" +
                        "ne;\\\\\\\"> </span><p style=\\\\\\\"color:#191919;font-family:&amp;font-size:100%;font-sty\n" +
                        "le:normal;font-weight:400;margin-left:0px;text-align:left;text-decoration:none;text\n" +
                        "-indent:0px;\\\\\\\"><span style=\\\\\\\"font-size:16px;\\\\\\\">3.如遇个人原因临时变更，请于原定拍摄时间提前<span style=\\\\\\\"font-size:100%;\\\\\\\">至少 48 小时</span>与我联系更改，感谢理解；预约当天无特殊理由取消，订单作废，再次预约重付 20 元定金。</span></p>\""
                vb.inspectCarText.text = Html.fromHtml(str)
            }
        }
        vb.inspectCarBtn.setOnClickListener {
            dismiss()
        }
    }
}