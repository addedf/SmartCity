package com.example.smartcity.bean

data class BusListBean(val rows: List<Data>) {
    data class Data(
        val id: Int,
        val name: String,
        val first: String,
        val end: String,
        val startTime: String,
        val endTime: String,
        val price: Float
    )
}