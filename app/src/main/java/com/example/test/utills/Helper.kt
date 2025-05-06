package com.example.test.utills

class Helper {

    fun isPalindrome(input:String) :Boolean{
        var str = input.lowercase().replace(" ","")
        if (str == str.reversed()){
            return true
        }else{
            return false
        }
    }
}