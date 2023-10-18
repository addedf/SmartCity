package com.example.smartcity.bean

data class ExpressBean(val data:List<Data>) {
    data class Data(val imgUrl: String, val name: String, val id: Int, val phone: String)
}