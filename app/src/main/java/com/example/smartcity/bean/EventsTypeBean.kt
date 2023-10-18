package com.example.smartcity.bean

data class EventsTypeBean(val data : List<Data>) {
    data class Data(val name : String, val sort : Int,val id : Int)
}