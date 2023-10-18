package com.example.smartcity.bean

data class RecHotBean(val data: List<Data>) {
    data class Data(val keyword: String, val searchTimes: Int)
}
