package com.example.smartcity.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.example.smartcity.OnItemClickListener
import com.example.smartcity.databinding.RegDialogBinding


class RegDialog(context:Context,private val listener: OnItemClickListener) : Dialog(context) {
    val TAG = "RegDialog"
    lateinit var vb : RegDialogBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = RegDialogBinding.inflate(layoutInflater)
        setContentView(vb.root)
        var sex = ""
        vb.regDialogRg.setOnCheckedChangeListener { _, checkedId ->
            val selectedRadioButton = findViewById<RadioButton>(checkedId)
            if (selectedRadioButton.isChecked) {
                sex = when(selectedRadioButton.text.toString()) {
                    "女" -> "1"
                    else -> "0"
                }
            }
        }
        vb.regDialogOk.setOnClickListener {
            val name = vb.regDialogName.text.toString()
            val address = vb.regDialogAddress.text.toString()
            val cardId = vb.regDialogCardId.text.toString()
            val tel = vb.regDialogTel.text.toString()
            listener.onUserInfo(name,sex,cardId,tel,address)
            dismiss()
        }
        vb.regDialogErr.setOnClickListener {
            dismiss()
        }
    }
}