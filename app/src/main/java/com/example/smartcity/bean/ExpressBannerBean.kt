package com.example.smartcity.bean

data class ExpressBannerBean(val data: List<Data>) {
    data class Data(val imgUrl: String)
}