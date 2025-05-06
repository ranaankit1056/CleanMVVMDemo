package com.example.test.user.domain.model

data class UserDomainData (
    val userList:List<UserDomain>,
    val message:String,
    val status:String)

data class UserDomain(
    val id:String,
    val userName:String,
    val userEmail:String,
    val userMobile:String,
    val addDate:String,

)