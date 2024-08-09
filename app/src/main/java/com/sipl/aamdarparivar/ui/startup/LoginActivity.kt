package com.sipl.aamdarparivar.ui.startup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.sipl.aamdarparivar.R
import com.sipl.aamdarparivar.databinding.ActivityLoginBinding
import com.sipl.aamdarparivar.repository.UserRepository
import com.sipl.aamdarparivar.ui.main.HomeActivity
import com.sipl.aamdarparivar.ui.main.RegistrationActivity
import com.sipl.aamdarparivar.utils.CustomProgressDialog
import com.sipl.aamdarparivar.utils.MyValidator
import com.sipl.aamdarparivar.viewmodel.LoginViewModel
import com.sipl.aamdarparivar.viewmodel.LoginViewModelFactory
import com.sipl.aamdarparivar.webservice.ApiClient
import com.sipl.aamdarparivar.webservice.BaseResponse

class LoginActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    lateinit var binding: ActivityLoginBinding
    lateinit var progressDialog: CustomProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        progressDialog = CustomProgressDialog(this)
        val apiService = ApiClient.create();
        val userRepository = UserRepository(apiService)
        val factory = LoginViewModelFactory(userRepository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        viewModel.loginData.observe(this) { response ->
            when (response) {
                is BaseResponse.Loading -> {
                    progressDialog.show()
                }

                is BaseResponse.Success -> {
                    progressDialog.dismiss()
                    Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                }

                is BaseResponse.Error -> {
                    progressDialog.dismiss()
                    Toast.makeText(
                        this,
                        "Login failed: ${response.exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        binding.btnLogin.setOnClickListener {

            if (validateFields()) {
                viewModel.loginUser(
                    binding.etMobile.text.toString().trim(),
                    binding.etPassword.text.toString().trim(),
                )
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
        binding.tvResetPassword.setOnClickListener {

            startActivity(Intent(this, RegistrationActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
    }

    private fun validateFields(): Boolean {
        val validationResults = ArrayList<Boolean>()
        // Validate First Name
        if (MyValidator.isValidMobileNumber(binding.etMobile.text.toString())) {
            binding.txLayoutEmail.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutEmail.error = getString(R.string.enter_mobile_number)
            validationResults.add(false)
        }
        if (MyValidator.isValidPassword(binding.etPassword.text.toString())) {
            binding.txLayoutPassword.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutPassword.error = getString(R.string.enter_valid_password)
            validationResults.add(false)
        }
        return !validationResults.contains(false)
    }

}