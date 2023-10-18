package com.example.smartcity.bean

data class EventsListBean(val rows: List<Data>) {
    data class Data(
        val createTime: String,
        val updateBy: String,
        val updateTime: String,
        val id: Int,
        val name: String,
        val content: String,
        val imgUrl: String,
        val recommend: String,
        val signupNum: String,
        val likeNum: String,
        val publishTime: String,
        val categoryName: String
    )
}