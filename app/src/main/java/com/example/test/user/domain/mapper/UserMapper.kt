package com.example.test.user.domain.mapper

import com.example.test.user.data.model.CommonResponse
import com.example.test.user.data.model.UserData
import com.example.test.user.data.model.UserListResponse
import com.example.test.user.domain.model.CommonDomainData
import com.example.test.user.domain.model.UserDomain
import com.example.test.user.domain.model.UserDomainData
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun mapCommonData(commonResponse: CommonResponse):CommonDomainData{
        return CommonDomainData(
            message = commonResponse.message,
            status = commonResponse.status)
    }

    fun mapUserList(userListResponse: UserListResponse):UserDomainData{
        return UserDomainData(
            userList = userListResponse.usersList.map { userData ->mapUserData(userData)  },
            message = userListResponse.message,
            status = userListResponse.status)
    }

    fun mapUserData(userData: UserData):UserDomain{
        return UserDomain(
            id = userData.user_id,
            userName = userData.user_name,
            userEmail = userData.user_email,
            userMobile = userData.user_mobile,
            addDate = userData.add_date)
    }
}