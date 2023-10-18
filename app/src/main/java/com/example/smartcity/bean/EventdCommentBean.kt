package com.example.smartcity.bean

data class EventdCommentBean(val rows : List<Data>) {
    data class Data(val createTime : String?,val nickName : String?,val activityId : Int?,val content :String?,val avatar : String?)
}