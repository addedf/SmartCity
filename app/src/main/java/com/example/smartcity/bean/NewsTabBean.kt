package com.example.smartcity.bean

data class NewsTabBean(val data : List<Data>) {
    data class Data(val name : String,val id : Int)
}