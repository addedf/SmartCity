package com.example.smartcity.bean

data class YouthInnListBean(val rows: List<Data>){
    data class Data(val name: String, val id: Int, val coverImgUrl: String, val bedsCountBoy: Int, val bedsCountGirl: Int, val address: String, val introduce: String)
}