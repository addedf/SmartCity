package com.example.smartcity.bean

data class StopCarInfoBean(val data : Data) {
    data class Data(val parkName:String,
    val address : String,
    val vacancy : String,
    val priceCaps : String,
    val imgUrl : String,
    val rates : String,
    val allPark : String,
    val open : String)
}