package com.example.test.user.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.test.common.ApiResultState
import com.example.test.databinding.ActivityAddUserBinding
import com.example.test.user.domain.model.CommonDomainData
import com.example.test.user.presentation.viewModels.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddUserBinding
    private val viewmodel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewmodel.addUser.observe(this){state->
            when(state){
                        ApiResultState.Loading -> loading()
                        is ApiResultState.Success -> success(state.data)
                        is ApiResultState.APIErrorMessage-> error(state.message)
                        is ApiResultState.ServerErrorMessage -> error(state.errorMessage)
            }

        }

        binding.btnSubmit.setOnClickListener {
            val name = binding.etvName.text.toString().trim()
            val email = binding.etvEmail.text.toString().trim()
            val mobile = binding.etvMobile.text.toString().trim()

            if (validateInputs(name, email, mobile)) {
                viewmodel.addUserData(name, mobile, email)
            }
        }


    }



    private fun validateInputs(name: String, email: String, mobile: String): Boolean {
        var isValid = true

        // Validate name
        if (name.isEmpty()) {
            binding.etvName.error = "Name is required"
            isValid = false
        }
        // Validate email
        if (email.isEmpty()) {
            binding.etvEmail.error = "Email is required"
            isValid = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etvEmail.error = "Invalid email format"
            isValid = false
        }
        // Validate mobile number
        if (mobile.isEmpty()) {
            binding.etvMobile.error = "Mobile number is required"
            isValid = false
        } else if (!mobile.matches(Regex("^[0-9]{10}$"))) {
            binding.etvMobile.error = "Invalid mobile number"
            isValid = false
        }

        return isValid
    }
    private fun loading(){
        binding.btnSubmit.isEnabled = false
        binding.btnSubmit.isClickable = false
    }
    private fun success(commonDomainData: CommonDomainData){
        Toast.makeText(this@AddUserActivity,commonDomainData.message , Toast.LENGTH_LONG).show()
        val resultIntent = Intent()
        resultIntent.putExtra("isRefresh", true)
        setResult(RESULT_OK, resultIntent)
        finish()

    }
    private fun error(message:String) {
        Toast.makeText(this@AddUserActivity,message , Toast.LENGTH_LONG).show()
        binding.btnSubmit.isEnabled = true
        binding.btnSubmit.isClickable = true
    }
}