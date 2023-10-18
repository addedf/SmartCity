package com.example.smartcity.bean

data class VohMainListBean(val rows : List<Data>) {
    data class Data(val id : Int,val title : String,val undertaker :String,val startAt :String,val requireText :String,val detail:String)
}