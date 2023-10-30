package com.example.smartcity.bean

class RegListBean(val rows: List<Data>) {
    data class Data(
        val createTime: String,
        val id: Int,
        val name :String,
        val cardId: String,
        val tel: String,
        val sex: String,
        val birthday: String,
        val address: String,
        val imgUrl: String, val userId: String
    )
}