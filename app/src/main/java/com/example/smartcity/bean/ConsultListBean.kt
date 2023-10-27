package com.example.smartcity.bean

class ConsultListBean(val rows : List<Data>) {
    data class Data(val id : Int,val state : String,val createTime : String,val legalExpertiseName :String,val lawyerName :String,val phone : String,val imageUrls :String,val content :String)
}