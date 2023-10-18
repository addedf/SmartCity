package com.example.smartcity.bean

data class CurriculumVitaeBean(val data: Data) {
    data class Data(val experience : String,val id: Int, val userId: Int, val mostEducation: String, val education: String,val address : String,val individualResume : String,val money: String)
}