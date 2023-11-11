package com.example.smartcity.bean

data class CarManageBean(val rows : List<Data>) {
    data class Data(val plateNo : String,val engineNo:String,val type:String,val id:Int,val userId:Int)
}