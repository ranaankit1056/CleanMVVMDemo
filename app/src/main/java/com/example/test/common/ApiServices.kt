package com.example.test.common

import com.example.test.user.data.model.CommonResponse
import com.example.test.user.data.model.UserListResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiServices {
    @FormUrlEncoded
    @POST("user_controller.php")
    suspend fun getUser(@Field("getUsers") getUsers:String) :Response<UserListResponse>

    @FormUrlEncoded
    @POST("user_controller.php")
    suspend fun addUser(@Field("addUser")addUser:String,
                        @Field("user_name")user_name:String,
                        @Field("user_mobile")user_mobile:String,
                        @Field("user_email")user_email:String):Response<CommonResponse>




}