package com.example.smartcity.bean

data class HotJobListBean(val rows : List<Rows>) {
    data class Rows(val id : Int , val professionName : String)
}