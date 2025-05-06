package com.example.test.utills

import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters


@RunWith(value = Parameterized::class)
class ParametrizedExample(val input:String,val expectedResult:Boolean) {

    companion object   {
        @JvmStatic
        @Parameters
        fun data(): List<Array<Any>> {
            return listOf(
                arrayOf("ANkit", false),
                arrayOf("rana", false),
                arrayOf("LEVEL",true)
            )
        }
    }

    @Test
    fun test() {
            val helper =Helper()
            val result = helper.isPalindrome(input)
            assertEquals(expectedResult,result)

        }

}