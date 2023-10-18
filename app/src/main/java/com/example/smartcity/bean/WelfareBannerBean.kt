package com.example.smartcity.bean

data class WelfareBannerBean(val data : List<Data>) {
    data class Data(val id : Int,val title : String,val imgUrl : String)
}