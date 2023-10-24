package com.example.smartcity.bean

data class PetInquiryBean(val rows : List<Data>) {
    data class Data(val createTime : String,val userId : String,val question : String,val imageUrls : String)
}