package com.example.smartcity.bean
//返回数据不是数组而是对象 不用List
data class ExpressInfoBean(val data : Data) {
    data class Data(val name: String, val imgUrl: String, val introduce: String, val shippingMethod: String, val phone: String)
}