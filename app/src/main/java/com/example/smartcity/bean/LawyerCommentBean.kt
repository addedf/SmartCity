package com.example.smartcity.bean

data class LawyerCommentBean(val rows: List<Data>) {
    data class Data(
        val fromUserName: String,
        val fromUserAvatar: String,
        val createTime: String,
        val evaluateContent: String,
        val score: Int,
        val likeCount : Int,
        val myLikeState : Boolean,
        val adviceId : Int
    )
}