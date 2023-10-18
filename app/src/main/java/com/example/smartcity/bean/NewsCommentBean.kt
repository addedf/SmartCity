package com.example.smartcity.bean

data class NewsCommentBean(val rows : List<Rows>) {
    data class Rows(val id:Int,val newsId : Int,val content : String,val commentDate : String,val likeNum : Int,val userName : String)
}