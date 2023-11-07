package com.example.smartcity.bean

data class DABean(val rows: List<Data>){
    data class Data(val likeNum: Int, val title: String)
}
