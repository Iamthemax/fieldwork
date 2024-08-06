package com.sipl.fieldwork.ui.startup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.sipl.fieldwork.R
import com.sipl.fieldwork.repository.UserRepository
import com.sipl.fieldwork.viewmodel.LoginViewModel
import com.sipl.fieldwork.viewmodel.LoginViewModelFactory
import com.sipl.fieldwork.webservice.ApiClient
import com.sipl.fieldwork.webservice.BaseResponse

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val apiService=ApiClient.create();
        val userRepository =UserRepository(apiService)
        val factory=LoginViewModelFactory(userRepository)
        viewModel=ViewModelProvider(this,factory).get(LoginViewModel::class.java)

        viewModel.loginData.observe(this){
            response->
                when(response){
                    is BaseResponse.Loading -> {
                       // progressBar.visibility = android.view.View.VISIBLE
                    }
                    is BaseResponse.Success -> {
                        //progressBar.visibility = android.view.View.GONE
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                    }
                    is BaseResponse.Error -> {
                        //progressBar.visibility = android.view.View.GONE
                        Toast.makeText(this, "Login failed: ${response.exception.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun loginUser() {
        viewModel.loginUser("")
    }

}