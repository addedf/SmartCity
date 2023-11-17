package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.OnItemClickListener
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.DianCanBean
import com.example.smartcity.bean.PayBean
import com.example.smartcity.databinding.ActivityTakeOutClosBinding
import com.example.smartcity.databinding.ItemOrderListBinding
import com.example.smartcity.dialog.AddressDialog
import com.example.smartcity.g
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.lang.Exception
import kotlin.properties.Delegates

class TakeOutClosActivity : AppCompatActivity(), OnItemClickListener {
    private val vb by viewBinding(ActivityTakeOutClosBinding::inflate)
    val TAG = "TakeOutClosActivity"
    lateinit var list: List<DianCanBean.DataBean>
    private lateinit var addressDetail: String
    private lateinit var label: String
    private lateinit var name2: String
    private lateinit var phone2: String
    private var index : Int = 0
    private var total : Double = 0.0
    private var id : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.takeOutTb.setOnClickListener {
            finish()
        }
        val data = intent.getStringExtra("data")
        list = g.fromJson(data, DianCanBean::class.java).data
        tool.apply {
            vb.takeOutClosList.adapter = GenericAdapter(list.size,
                { ItemOrderListBinding.inflate(layoutInflater) }) { binding, position ->
                if (list[position].index >= 1) {
                    binding.itemOrderName.text = list[position].name
                    binding.itemOrderTotal.text = "X" + list[position].index.toString()
                    binding.itemOrderPrice.text = list[position].total.toString() + "元"
                    Glide.with(context).load(getUrl(list[position].imgUrl))
                        .into(binding.itemOrderImg)
                    index = list[position].index
                    id = list[position].id
                    total = list[position].total
                } else {
                    binding.root.visibility = View.GONE
                }
                vb.takeOutClosBtn.setOnClickListener {
                    try {
                        val data = """
                {
                "addressDetail": "$addressDetail",
                "label": "$label",
                "name": "$name2",
                "phone": "$phone2",
                "amount": ${total},
                "orderItemList": [
                {
                "productId": ${id},
                "quantity": $index
                }
                ],
                "sellerId": ${list[position].sellerId}
                }
            """.trimIndent()
                        val req = data.toRequestBody("application/json".toMediaTypeOrNull())
                        send("/prod-api/api/takeout/order/create","POST",req,true) {
                            if (it.contains("操作成功")) {
                                val data = g.fromJson(it,PayBean::class.java).orderNo
                                val intent = Intent(context,TakePayActivity::class.java)
                                intent.putExtra("orderNo",data)
                                intent.putExtra("total",total)
                                startActivity(intent)
                            } else {
                                Toast.makeText(context,JSONObject(it).toString(),Toast.LENGTH_SHORT).show()
                            }
                        }
                    } catch (e:Exception) {
                        Toast.makeText(context,"您还未选择地址",Toast.LENGTH_SHORT).show()
                    }
                }
            }
            vb.takeOutClosList.layoutManager = LinearLayoutManager(context)
        }
        tool.apply {
            vb.takeOutClosAddress.setOnClickListener {
                AddressDialog(context, this@TakeOutClosActivity).show()
            }
        }
    }

    override fun onItemClick(id: Int) {
    }

    override fun onUserInfo(
        name: String,
        sex: String,
        userId: String?,
        phone: String,
        address: String
    ) {
        addressDetail = name
        label = sex
        name2 = phone
        phone2 = address
        vb.takeOutClosAddress.text = addressDetail
    }

    override fun onCarInfo(engineNo: String, plateNo: String, type: String) {
    }
}