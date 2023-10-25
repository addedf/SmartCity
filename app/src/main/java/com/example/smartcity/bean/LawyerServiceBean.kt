package com.example.smartcity.bean

data class LawyerServiceBean(val rows : List<Data>) {
    data class Data(val id : Int,val name : String,val imgUrl : String,val sort : Int)
}