package com.example.smartcity.bean

data class GovernmentBannerBean(val data : List<Data>) {
    data class Data(val imgUrl : String)
}