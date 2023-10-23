package com.example.smartcity.bean

data class PetTypeBean(val data :List<Data>) {
    data class Data(val id : Int,val name : String,val imgUrl : String)
}