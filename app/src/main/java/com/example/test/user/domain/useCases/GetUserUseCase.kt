package com.example.test.user.domain.useCases

import com.example.test.user.data.repository.UserRepositoryIMPL
import com.example.test.user.domain.model.UserDomainData
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val userRepositoryIMPL: UserRepositoryIMPL) {

    suspend operator fun invoke():UserDomainData{
        return userRepositoryIMPL.getUser()
    }
}