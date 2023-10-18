package com.example.smartcity.bean

data class NewsInfoBean(val data: Data) {
    data class Data(
        val createTime: String,
        val updateTime: String,
        val id: Int,
        val cover: String,
        val title: String,
        val content: String,
        val status : String,
        val commentNum : Int,
        val likeNum : Int,
        val readNum : Int
        )
}