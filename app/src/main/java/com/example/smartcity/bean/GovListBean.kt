package com.example.smartcity.bean

data class GovListBean(val rows: List<Data>) {
    data class Data(
        val createTime: String,
        val id: Int,
        val title: String,
        val content: String,
        val undertaker: String,
        val appealCategoryName: String,
        val detailResult:String,
        val imgUrl : String?
    )
}