package com.example.smartcity.bean

data class BusInfoBean(val data:Data) {
    data class Data(
        val name: String,
        val first: String,
        val end: String,
        val startTime: String,
        val endTime: String,
        val price: Float,
        val mileage :String
    )
}