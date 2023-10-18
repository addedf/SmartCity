package com.example.smartcity.bean

data class NewsListBean(val rows: List<Data>) {
    data class Data(
        val id: Int,
        val cover: String,
        val title: String,
        val content: String,
        val publishDate: String,
        val commentNum: Int,
        val createTime: String
    )
}