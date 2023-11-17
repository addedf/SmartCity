package com.example.smartcity.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartcity.OnItemClickListener
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.AddressListBean
import com.example.smartcity.databinding.DialogAddressBinding
import com.example.smartcity.databinding.ItemAddCarInfoDialogBinding
import com.example.smartcity.databinding.ItemDialogAddressListBinding
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.ui.activity.AddressInfoActivity
import com.example.smartcity.ui.activity.TakeOutClosActivity

class AddressDialog(context: Context, private val listener: OnItemClickListener) : Dialog(context) {
    lateinit var vb : DialogAddressBinding
    val TAG = "AddressDialog"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = DialogAddressBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(vb.root)
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.attributes = params
        loadList()
        vb.dialogAddressBtn.setOnClickListener {
            context.startActivity(Intent(context, AddressInfoActivity::class.java))
            dismiss()
        }
    }

    private fun loadList() {
        tool.apply {
            send("/prod-api/api/takeout/address/list","GET",null,true) {
                val data = g.fromJson(it,AddressListBean::class.java).data
                vb.dialogAddressList.adapter = GenericAdapter(data.size,
                    { ItemDialogAddressListBinding.inflate(layoutInflater) }) { binding,position ->
                    binding.itemDialogAddressAddressDetail.text = data[position].addressDetail
                    binding.itemDialogAddressLabel.text = data[position].label
                    binding.itemDialogAddressName.text = data[position].name
                    binding.itemDialogAddressPhone.text = data[position].phone
                    binding.root.setOnClickListener {
                        listener.onUserInfo(data[position].addressDetail,data[position].label,null,data[position].name,data[position].phone)
                        dismiss()
                    }
                }
                vb.dialogAddressList.layoutManager = LinearLayoutManager(context)
            }
        }
    }
}