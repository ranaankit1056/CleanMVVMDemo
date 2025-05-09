package com.example.test.user.data.repository

import com.example.test.user.domain.mapper.UserMapper
import com.example.test.user.domain.model.UserDomainData
import com.example.test.common.ApiServices
import com.example.test.user.domain.model.CommonDomainData
import javax.inject.Inject

class UserRepositoryIMPL @Inject constructor(private val apiServices: ApiServices,
                                             private val userMapper: UserMapper) {

    suspend fun addUser(name:String,mobile:String,email:String):CommonDomainData{
        val response = apiServices.addUser("addUser",name,mobile,email)
        if(response.isSuccessful){
            val responseBody = response.body()
            return userMapper.mapCommonData(responseBody!!)
        }else{
            throw Exception(response.message())
        }
    }
    suspend fun getUser():UserDomainData{
        val response = apiServices.getUser("getUsers")
        if (response.isSuccessful){
            val responseBody = response.body()
            return userMapper.mapUserList(responseBody!!)
        }else{
            throw Exception(response.message())
        }
    }


}