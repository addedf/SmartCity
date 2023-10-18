package com.example.smartcity.bean

data class WelfareInfoBean(val rows : List<Data>) {
    data class Data(val donateMoney : Int,val userName : String,val activityName : String,val createTime : String)
}