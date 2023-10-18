package com.example.smartcity.bean

data class MovieBean(val rows : List<Rows>) {
    data class Rows(val advImg : String)
}