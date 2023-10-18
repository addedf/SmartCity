package com.example.smartcity.bean

data class WelfareTypeBean(val data: List<Data>) {
    data class Data(val name: String, val id: Int, val imgUrl: String)
}