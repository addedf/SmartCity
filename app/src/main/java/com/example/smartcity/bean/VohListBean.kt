package com.example.smartcity.bean

data class VohListBean(val rows : List<Data>) {
    data class Data(val createTime : String,val id : Int,val title : String ,val imgUrl :String,val summary : String)
}