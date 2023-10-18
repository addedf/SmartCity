package com.example.smartcity.bean

data class HouseBean(val rows: List<Rows>) {
    data class Rows(
        val id: Int,
        val sourceName: String,
        val address: String,
        val areaSize: Int,
        val tel: String,
        val price: String,
        val houseType: String,
        val pic: String,
        val description: String
    )
}