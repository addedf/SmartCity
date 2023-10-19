package com.example.smartcity.bean

data class GovTypeBean(val rows : List<Data>) {
    data class Data(val id : Int,val name : String,val sort : String,val imgUrl : String)
}