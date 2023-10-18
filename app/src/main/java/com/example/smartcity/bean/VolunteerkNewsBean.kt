package com.example.smartcity.bean

data class VolunteerkNewsBean(val data : Data) {
    data class Data(val createTime : String,val title : String,val imgUrl : String,val summary : String,val content : String)
}