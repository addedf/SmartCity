package com.example.smartcity.bean

data class MovieListBean(val rows: List<Rows>) {
    data class Rows(
        val id: Int,
        val name: String,
        val cover: String,
        val duration: String,
        val playDate: String,
        val likeNum : Int,
        val favoriteNum : Int,
        val language : String,
        val introduction : String
    )
}