package com.example.test.utills

import android.util.Log
import org.junit.After
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

 class HelperTest {

     @After
     fun after(){
         Log.e("##", "After")
     }

     @Before
     fun before(){
         Log.e("##", "Before")
     }
@Test
 fun isPalindrome_input_string_level_expected_true() {
    //Arrange
    val helper = Helper()
    //Act

    val result = helper.isPalindrome("LEVEL")
   //Assert
   assertEquals(true,result)
 }

     @Test
     fun isPalindrome_input_string_hello_expected_false() {
         //Arrange
         val helper = Helper()
         //Act
         val result = helper.isPalindrome("Hello")
         //Assert
         assertEquals(false,result)
     }
}