package com.example.smartcity.bean

class JobListBean(val rows: List<Rows>) {
    data class Rows(val professionName: String, val obligation: String, val address: String, val salary: String, val id: Int)
}