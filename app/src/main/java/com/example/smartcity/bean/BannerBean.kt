package com.example.smartcity.bean


data class BannerBean(val rows: List<Data>) {
    data class Data(val advImg: String)
}