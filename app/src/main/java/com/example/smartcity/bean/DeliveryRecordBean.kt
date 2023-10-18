package com.example.smartcity.bean

data class DeliveryRecordBean(val rows: List<Rows>) {
    data class Rows(
        val id: Int,
        val userId: Int,
        val companyId: Int,
        val postName: String,
        val companyName: String,
        val money: String,
        val satrTime: String,
        val userName: String
    )
}