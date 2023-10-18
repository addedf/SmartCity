package com.example.smartcity.bean

data class RecCommentBean(val rows : List<Data>) {
    data class Data(val createTime : String,val content : String,val userId : Int)
}