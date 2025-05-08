package com.example.test.user.presentation.activity

import android.util.Log

enum class Status(val status:String) {
    SUCCESS("200"),
    FAILED("201"),
    NOT_FOUND("404");

    fun printMessage() {
        Log.e("##",status)
    }

}