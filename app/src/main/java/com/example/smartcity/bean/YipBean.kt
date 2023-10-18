package com.example.smartcity.bean

data class YipBean(val data: Data?){
    data class Data(val title: String, val content: String)
}