package com.example.smartcity.bean

data class PetInqioryBean(val rows: List<Data>) {
    data class Data(val createTime: String, val question: String, val imageUrls: String,val doctor : Doctor)
    data class Doctor(val name : String,val avatar : String,val jobName :String,val practiceNo :String,val goodAt :String)
}