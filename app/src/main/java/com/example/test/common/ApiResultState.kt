package com.example.test.common

sealed class ApiResultState<out T> {
    object Loading:ApiResultState<Nothing>()
    data class Success<out T>(val data: T):ApiResultState<T>()
    data class APIErrorMessage(val message:String):ApiResultState<Nothing>()
    data class ServerErrorMessage(val errorMessage: String):ApiResultState<Nothing>()

}