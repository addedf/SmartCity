package com.example.smartcity.bean

data class EventsBannerBean(val rows : List<Data>) {
    data class Data(val id :Int,val advImg : String)
}