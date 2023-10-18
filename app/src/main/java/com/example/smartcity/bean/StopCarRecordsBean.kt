package com.example.smartcity.bean

data class StopCarRecordsBean(val rows: List<Rows>) {
    data class Rows(
        val id: Int,
        val lotId: Int,
        val entryTime: String,
        val outTime: String,
        val plateNumber: String,
        val monetary : String,
        val parkName : String,
        val parkNo : String,
        val address : String
    )
}