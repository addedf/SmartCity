package com.example.smartcity.bean

data class VoInfoBean(val data: Data){
    data class Data(val title: String, val undertaker: String, val startAt: String, val requireText: String, val detail: String)
}
