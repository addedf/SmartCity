package com.example.smartcity.bean

data class BusOrderListBean(val rows: List<Data>) {
    data class Data(
        val createTime: String,
        val updateTime: String,
        val id: Int,
        val orderNum: String,
        val path: String,
        val start: String,
        val end: String,
        val price :Float,
        val userName :String,
        val userTel :String,
        val userId :Int,
        val paymentType :String,
        val payTime :String
    )
}