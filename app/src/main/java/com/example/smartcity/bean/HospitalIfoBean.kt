package com.example.smartcity.bean

data class HospitalIfoBean(val data : Data) {
    data class Data(val id :Int,val hospitalName:String,val brief :String,val level :Int,val imgUrl :String)
}