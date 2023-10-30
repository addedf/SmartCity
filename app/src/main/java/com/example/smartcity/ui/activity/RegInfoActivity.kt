package com.example.smartcity.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RadioButton
import android.widget.Toast
import com.example.smartcity.R
import com.example.smartcity.databinding.ActivityRegInfoBinding
import com.example.smartcity.jump
import com.example.smartcity.tool
import com.example.smartcity.viewBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RegInfoActivity : AppCompatActivity() {
    val TAG = "RegInfoActivity"
    private val vb by viewBinding(ActivityRegInfoBinding::inflate)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(vb.root)
        setSupportActionBar(vb.RegInfoTb)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val id = intent.getIntExtra("id",0)
        val address = intent.getStringExtra("address")
        val cardId = intent.getStringExtra("cardId")
        val userId = intent.getStringExtra("userId")
        val sex = intent.getStringExtra("sex")
        val tel = intent.getStringExtra("tel")
        val name = intent.getStringExtra("name").toString()
        vb.RegInfoName.setText(name)
        vb.RegInfoAddress.setText(address)
        vb.RegInfoCardId.setText(cardId)
        vb.RegInfoTel.setText(tel)
        when(sex){
            "0" -> vb.regDialogMan.isChecked = true
            "1" -> vb.regDialogWoman.isChecked = true
        }
        vb.regInfoUpdata.setOnClickListener {
            var sex = ""
            vb.RegInfoRg.setOnCheckedChangeListener { _, checkedId ->
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                if (selectedRadioButton.isChecked) {
                    sex = when(selectedRadioButton.text.toString()) {
                        "女" -> "1"
                        else -> "0"
                    }
                }
            }
            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val formattedDate = currentDateTime.format(formatter)
            val data = """
            {
            "id": $id,
            "address": "${vb.RegInfoAddress.text}",
            "birthday": "$formattedDate",
            "cardId": "${vb.RegInfoCardId.text}",
            "name": "${vb.RegInfoName.text}",
            "sex": "$sex",
            "tel": "${vb.RegInfoTel.text}"
            }
        """.trimIndent()
            val req = data.toRequestBody("application/json".toMediaTypeOrNull())
            tool.apply {
                send("/prod-api/api/hospital/patient","PUT",req,true) {
                    if (it.contains("操作成功")) {
                        Toast.makeText(context,"修改成功", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, JSONObject(it).getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.reg_info,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.reg_info_btn -> {

            }
        }
        return true
    }
}