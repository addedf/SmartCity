package com.example.smartcity.bean

data class SearchContenBean(val data: Data) {
    data class Data(val createTime: String, val infoNo: String, val company:Company,val stepList:List<Rows>)
//    快递公司是对象数据类
    data class Company(val name : String,val imgUrl: String,val introduce :String)
//    快递发货则是数组所以要区分开
    data class Rows(val eventAt:String,val stateName : String,val addressAt : String)
}