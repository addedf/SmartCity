package com.example.smartcity.bean

data class HotBean(val rows: List<Rows>) {
    data class Rows(
        val cover: String,
        val title: String,
        val content: String,
        val createTime: String,
        val updateTime: String,
        val commentNum: Int,
        val likeNum: Int,
        val readNum: Int
    )
}