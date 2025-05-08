package com.example.test.user.presentation.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.common.ApiResultState
import com.example.test.databinding.ActivityMainBinding
import com.example.test.user.domain.model.UserDomain
import com.example.test.user.domain.model.UserDomainData
import com.example.test.user.presentation.adapter.UserAdapter
import com.example.test.user.presentation.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val userViewModel:UserViewModel by viewModels()
    private lateinit var userAdapter:UserAdapter
    private lateinit var binding:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.EtvSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userAdapter.searchUser(p0.toString())

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //-- Extension function for String
        //allow you to add new functionality to existing classes without modifying their original implementation.
        val message = "hello, kotlin!"
        println(message.capitalizeFirstLetter())
        // Output: "Hello, kotlin!"

        //--- TOP LEVEL Function
        // use function without create instances of the class  in same module
        showData("Dada")
        //-- Singleton Class
        // has only one instance throughout the app and no need for manual instance creation
        SingletonClass.showData()
        //-- Enum Demo
        //fix value
        Status.SUCCESS.printMessage()
        //----Sealed Class Demo
        //Type Fix but value can change for controlled hierarchy
        userViewModel.userList.observe(this){state->
            when(state){
                //-------is Operator – Type Checking
                ApiResultState.Loading ->loading()
                is ApiResultState.Success ->success(state.data)
                is ApiResultState.APIErrorMessage -> error(state.message)
                is ApiResultState.ServerErrorMessage -> error(state.errorMessage)
            }
        }
        //---as Safe Type Casting
        safeCastExample("Hello")  // Output: 5
        safeCastExample(123)      // Output: Not a String

        //--- Higher-Order Function
        //---Takes another function as a parameter, or Returns a function as a result.
        val sumResult = operateOnNumbers(5, 3) { x, y -> x + y }
        Log.e("##","" + sumResult)
        //--Output: 8
        val multiplyResult = operateOnNumbers(5, 3) { x, y -> x * y }
        Log.e("##","" + multiplyResult)
        //-- Output : 15

        //---Higher-Order Functions with Collections
        val numbers = listOf(1, 2, 3, 4, 5)

        val filterNumbers = numbers.filter { it %2 ==0 }
        Log.e("##","" + filterNumbers)
        //--- Output: [2,4]

        val squaredNumbers = numbers.map { it * it }
        Log.e("##","" + squaredNumbers)
        //--- Output: [1, 4, 9, 16, 25]

        val sum = numbers.reduce { acc, num -> acc + num }
        Log.e("##","" + sum)
        // Output: 15


        // Palette API
        // is used to extract prominent colors from images to create dynamic and
        // visually engaging UI designs. It helps developers generate color schemes
        // from images and apply them to UI elements like backgrounds, text, and buttons.


    }

    fun String.capitalizeFirstLetter(): String {
        return this.replaceFirstChar { it.uppercase() }
    }

    fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
        return operation(a, b)
    }

    private fun safeCastExample(value: Any) {
        val str: String? = value as? String
        println(str?.length ?: "Not a String")
    }

    private fun loading(){

    }
    private fun success(userDomainData: UserDomainData){
       if (userDomainData.status.equals("200")){
            userAdapter = UserAdapter(userDomainData.userList)
            binding.ListData.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            binding.ListData.adapter = userAdapter

            userAdapter.SetUpInteface(object:UserAdapter.ItemClick{
                override fun DataClick(userDomain: UserDomain) {
                    Toast.makeText(this@MainActivity ,userDomain.userName,Toast.LENGTH_LONG).show()
                }

            })


        }
    }

    private fun error(message:String){

       Toast.makeText(this@MainActivity,message,Toast.LENGTH_LONG).show()
        val fn=::isPalindrome
        Log.e("##","fd" + fn);

    }

    fun isPalindrome(input:String) :Boolean{
        val str = input.lowercase().replace(" ","")
        if (str == str.reversed()){
            return true
        }else{
            return false
        }
    }

}