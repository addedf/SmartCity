package com.example.smartcity.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.smartcity.*
import com.example.smartcity.adapter.GenericAdapter
import com.example.smartcity.bean.LawyerScreenBean
import com.example.smartcity.databinding.ActivityConsultListBinding
import com.example.smartcity.databinding.ItemHotLawyerListBinding
import com.example.smartcity.dialog.ScreenDialog
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConsultListActivity : AppCompatActivity(), OnItemClickListener {
    private val vb by viewBinding(ActivityConsultListBinding::inflate)
    val TAG = "ConsultListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        vb.consultSort.setOnClickListener {
            loadList(null, "favorableRate")
        }
        vb.consltBtn.setOnClickListener {
            tool.apply {
                val ScreenDialog = ScreenDialog(this@ConsultListActivity, this@ConsultListActivity)
                // 显示Dialog
                ScreenDialog.show()
            }

        }
        loadList(null, "workStartAt")
    }

    fun onDialogClick(value: Int) {
        // 处理从Dialog传回的值
        loadList(value, "favorableRate")
    }

    private fun loadList(id: Int?, s: String) {
        val url = if (id != null) {
            "/prod-api/api/lawyer-consultation/lawyer/list?legalExpertiseId=$id&sort=$s"
        } else {
            "/prod-api/api/lawyer-consultation/lawyer/list?sort=${s}"
        }
        tool.apply {
            send(url, "GET", null, true) {
                val data = g.fromJson(it, LawyerScreenBean::class.java).rows
                vb.consultList.adapter = GenericAdapter(data.size,
                    { ItemHotLawyerListBinding.inflate(layoutInflater) }) { binding, position ->
                    binding.root.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    binding.root.setOnClickListener {
                        val intent = Intent(context, ConsultActivity::class.java)
                        intent.putExtra("id", data[position].id)
                        intent.putExtra("legalExpertiseId", data[position].legalExpertiseId)
                        startActivity(intent)
                    }
                    binding.itemLawyerBaseInfo.text = "介绍:${data[position].baseInfo}"
                    binding.itemLawyerName.text = data[position].name
                    binding.itemLawyerLegalExpertiseId.text =
                        "从业时长:${data[position].legalExpertiseId}年"
                    binding.itemLawyerLegalExpertiseName.text =
                        "法律专长:${data[position].legalExpertiseName}"
                    Glide.with(context).load(getUrl(data[position].avatarUrl))
                        .into(binding.itemLawyerAvatarUrl)
                }
                vb.consultList.layoutManager = object : LinearLayoutManager(context) {
                    override fun canScrollVertically(): Boolean = false
                }
            }
        }
    }

    //    重写方法处理点击传递回来的参数
    override fun onItemClick(id: Int) {
        onDialogClick(id)
    }

    override fun onUserInfo(
        name: String,
        sex: String,
        userId: String?,
        phone: String,
        address: String
    ) {
        TODO("Not yet implemented")
    }


    override fun onCarInfo(engineNo: String, plateNo: String, type: String) {
        TODO("Not yet implemented")
    }

}