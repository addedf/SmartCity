package com.example.smartcity.bean

data class LawyerHotListBean(val data: List<Data>) {
    data class Data(
        val id: Int,
        val name: String,
        val legalExpertiseId: Int,
        val avatarUrl: String,
        val baseInfo: String,
        val eduInfo: String,
        val licenseNo: String,
        val certificateImgUrl: String,
        val workStartAt: String,
        val legalExpertiseName: String,
        val serviceTimes :Int,
        val favorableRate :Int,
    )
}