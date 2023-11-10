package com.example.smartcity


//传参接口
interface OnItemClickListener {
    fun onItemClick(id: Int)
    fun onUserInfo(name: String, sex:String, userId:String, phone:String, address:String)
    fun onCarInfo(engineNo: String, plateNo: String, type: String)
}