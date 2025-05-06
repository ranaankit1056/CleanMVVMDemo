package com.example.test.user.data.model

data class UserListResponse(
    val usersList :List<UserData>,
    val message:String,
    val status:String)

data class UserData(
    val user_id:String,
    val user_name:String,
    val user_email:String,
    val user_mobile:String,
    val add_date:String)