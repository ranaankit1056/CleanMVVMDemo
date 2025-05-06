package com.example.test.user.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.user.domain.model.UserDomainData
import com.example.test.user.domain.useCases.GetUserUseCase
import com.example.test.common.ApiResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase):ViewModel(){

    private val _userList = MutableLiveData<ApiResultState<UserDomainData>>()
    val userList : LiveData<ApiResultState<UserDomainData>>get() = _userList


    init {
       loadData()
    }

    fun loadData(){

        _userList.value = ApiResultState.Loading

        viewModelScope.launch {

            try {
                val result = getUserUseCase()
                _userList.value = ApiResultState.Success(result!!)
            } catch (e: Exception) {
                _userList.value = ApiResultState.APIErrorMessage(e.localizedMessage)
            }
        }





    }

}