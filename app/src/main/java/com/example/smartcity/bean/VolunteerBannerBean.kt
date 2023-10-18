package com.example.smartcity.bean

data class VolunteerBannerBean(val data : List<Data>) {
    data class Data(val imgUrl:String)
}