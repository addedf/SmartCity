package com.example.smartcity.bean

data class RecNewsListBean(val rows: List<Row>){
    data class Row(val imgUrl: String, val createTime: String,val title: String, val id: Int,val author : String)
}