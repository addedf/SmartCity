package com.example.smartcity.bean

data class UserInfoBean(val user : User) {
    data class User(val nickName :String,val avatar :String, val sex : String,val phonenumber : String,val email : String,val idCard : String)
}