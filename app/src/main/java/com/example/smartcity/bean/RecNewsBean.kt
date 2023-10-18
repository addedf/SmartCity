package com.example.smartcity.bean

data class RecNewsBean(val data: Data){
    data class Data(val title: String, val content: String)
}