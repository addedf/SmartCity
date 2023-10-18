package com.example.smartcity.bean

data class RecNewsClazzBean(val rows: List<Row>){
    data class Row(val name: String, val id: Int, val sort: Int)
}