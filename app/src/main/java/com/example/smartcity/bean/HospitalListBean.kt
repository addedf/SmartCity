package com.example.smartcity.bean

class HospitalListBean(val rows :List<Data>) {
    data class Data(val id :Int,val hospitalName:String,val brief :String,val level :Int,val imgUrl :String)
}