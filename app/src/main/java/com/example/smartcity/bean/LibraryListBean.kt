package com.example.smartcity.bean

data class LibraryListBean(val rows: List<Data>) {
    data class Data(val name: String, val address: String, val imgUrl: String, val businessHours: String, val businessState: String, val id: Int, val description: String)
}