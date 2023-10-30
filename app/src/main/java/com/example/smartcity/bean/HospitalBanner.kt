package com.example.smartcity.bean

data class HospitalBanner(val data: List<Data>) {
    data class Data(val imgUrl: String)
}