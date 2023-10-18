package com.example.smartcity.bean

data class YouthInnInfoBean(val data: Data) {
    data class Data(
        val name: String,
        val imageUrls: String,
        val address: String,
        val bedsCountBoy: Int,
        val bedsCountGirl: Int,
        val phone: String,
        val workTime: String,
        val introduce: String,
        val internalFacilities: String,
        val surroundingFacilities: String,
        val specialService: String
    )
}