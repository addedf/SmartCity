package com.example.smartcity.bean

data class CallDoctorBean(val rows : List<Data>) {
    data class Data(val id :Int,val name : String,val avatar :String,val jobName :String,val practiceNo :String,val goodAt:String)
}