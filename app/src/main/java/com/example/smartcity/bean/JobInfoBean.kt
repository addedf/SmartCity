package com.example.smartcity.bean

data class JobInfoBean(val data: Data) {
    data class Data(
        val id: Int,
        val companyId: Int,
        val contacts: String,
        val name: String,
        val obligation: String,
        val address: String,
        val need: String,
        val salary: String,
        val companyName : String,
        val professionName : String
    )
}