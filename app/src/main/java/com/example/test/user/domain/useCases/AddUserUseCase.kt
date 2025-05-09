package com.example.test.user.domain.useCases

import com.example.test.user.data.repository.UserRepositoryIMPL
import com.example.test.user.domain.model.CommonDomainData
import javax.inject.Inject

class AddUserUseCase @Inject constructor(private val userRepositoryIMPL: UserRepositoryIMPL) {
    suspend operator fun invoke(name:String,mobile:String,email:String):CommonDomainData{
        return userRepositoryIMPL.addUser(name,mobile,email)
    }
}