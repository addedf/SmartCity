package com.example.smartcity.bean

data class LawyerInfoBean(val data: Data) {
    data class Data(
        val id: Int,
        val name: String,
        val legalExpertiseId: Int,
        val avatarUrl: String,
        val baseInfo: String,
        val eduInfo: String,
        val licenseNo : String,
        val certificateImgUrl : String,
        val workStartAt : String,
        val serviceTimes : Int,
        val favorableRate : Int,
        val legalExpertiseName : String,
        val favorableCount : Int
    )
}