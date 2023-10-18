package com.example.smartcity.bean

data class StopCarBean(val rows: List<Data>) {
    data class Data(
        val id: Int?,
        val parkName: String?,
        val vacancy: String?,
        val priceCaps: String?,
        val imgUrl: String,
        val rates: String?,
        val address: String?,
        val distance: String?,
        val allPark: String?,
        val open: String?
    )
}