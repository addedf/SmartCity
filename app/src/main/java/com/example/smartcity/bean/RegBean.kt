package com.example.smartcity.bean

data class RegBean(val rows:List<Data>) {
    data class Data(val id : Int,val type : String,val categoryName : String,val money : Float,val reserveTime :String,val patientName:String,val categoryId :Int)
}