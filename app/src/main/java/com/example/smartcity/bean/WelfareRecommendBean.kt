package com.example.smartcity.bean

data class WelfareRecommendBean(val rows: List<Data>) {
    data class Data(
        val name: String,
        val author: String,
        val moneyTotal: Long,
        val moneyNow: Long,
        val donateCount: Int,
        val imgUrl: String,
        val id: Int,
        val type : Type
    )
    data class Type(val name : String,val imgUrl : String)
}