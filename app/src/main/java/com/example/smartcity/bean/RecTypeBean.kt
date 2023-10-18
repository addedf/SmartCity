package com.example.smartcity.bean

data class RecTypeBean(val rows : List<Data>) {
    data class Data(val id :Int,val name :String,val imgUrl : String,val introduce : String,val guide : String)
}