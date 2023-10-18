package com.example.smartcity.bean
//后端返回的是对象而不是数组所以不需要list
data class HouseDetailsBean(val data: HouseDetails) {
//    问号表示该字段可能为空
    data class HouseDetails(
        val id: Int,
        val sourceName: String?,
        val address: String?,
        val areaSize: Int?,
        val tel: String?,
        val price: String?,
        val houseType: String?,
        val pic: String?,
        val description: String?
    )
}

