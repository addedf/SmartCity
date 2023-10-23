package com.example.smartcity.bean

data class PetDoctorLIstBean(val rows: List<Data>) {
    data class Data(
        val id: Int,
        val typeId: Int,
        val name: String,
        val avatar: String,
        val jobName: String,
        val practiceNo: String,
        val workingYears:Int,
        val goodAt : String
    )
}