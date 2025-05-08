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
import com.example.test.utills.PreferenceManager
import com.example.test.utills.PreferenceManager.getObject
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

        PreferenceManager.init(this)


        binding.EtvSearch.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                userAdapter.searchUser(p0.toString())

            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })

        //-- PreferenceManager

        PreferenceManager.setKeyValueString("user_id","202")
        Log.e("##","" + PreferenceManager.getKeyValueString("user_id"))

        //-- 1) Generic Class
        //a generic class allows you to define a class that can work with different data types while maintaining type safety.
        val intBox = Box(10) // Box holding an Int
        val stringBox = Box("Hello") // Box holding a String
        Log.e("##","" + intBox.getItem())
        // Output: 10
        Log.e("##", stringBox.getItem())
        // Output: Hello


        //-- 2) Extension function for String
        //allow you to add new functionality to existing classes without modifying their original implementation.
        val message = "hello, kotlin!"
        println(message.capitalizeFirstLetter())
        // Output: "Hello, kotlin!"

        //--- 3) TOP LEVEL Function
        // use function without create instances of the class  in same module
        showData("Dada")
        //-- 4) Singleton Class
        // has only one instance throughout the app and no need for manual instance creation
        SingletonClass.showData()
        //-- 5) Enum Demo
        //fix value
        Status.SUCCESS.printMessage()
        //----6) Sealed Class Demo
        //Type Fix but value can change for controlled hierarchy
        userViewModel.userList.observe(this){state->
            when(state){
                //-------7) is Operator – Type Checking
                ApiResultState.Loading ->loading()
                is ApiResultState.Success ->success(state.data)
                is ApiResultState.APIErrorMessage -> error(state.message)
                is ApiResultState.ServerErrorMessage -> error(state.errorMessage)
            }
        }
        //---8) as Safe Type Casting
        safeCastExample("Hello")  // Output: 5
        safeCastExample(123)      // Output: Not a String

        //--- 9) Higher-Order Function
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


        // 10) Palette API (Check PaletteActivity)
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

            if (userDomainData.userList.size>0){
                PreferenceManager.setObject("user_data",userDomainData)
            }
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

        val retrievedUser: UserDomainData? = PreferenceManager.getSharedPreferences().getObject("user_data")
        if(retrievedUser!!.userList.isNotEmpty()){
            userAdapter = UserAdapter(retrievedUser!!.userList)
            binding.ListData.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
            binding.ListData.adapter = userAdapter
            userAdapter.SetUpInteface(object:UserAdapter.ItemClick{
                override fun DataClick(userDomain: UserDomain) {
                    Toast.makeText(this@MainActivity ,userDomain.userName,Toast.LENGTH_LONG).show()
                }

            })

        }



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

    class Box<T>(private var item: T) {
        fun getItem(): T {
            return item
        }

        fun setItem(newItem: T) {
            item = newItem
        }
    }

}