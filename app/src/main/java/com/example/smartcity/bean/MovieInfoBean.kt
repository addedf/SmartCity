package com.example.smartcity.bean

data class MovieInfoBean(val data : Data) {
    data class Data(val id : Int,val name :String,val cover : String,val playDate :String,val duration :Int,val likeNum :Int , val score : Int)
}