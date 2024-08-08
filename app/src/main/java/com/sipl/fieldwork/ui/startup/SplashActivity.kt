package com.sipl.fieldwork.ui.startup

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.sipl.fieldwork.R
import com.sipl.fieldwork.databinding.ActivitySplashBinding
import com.sipl.fieldwork.databinding.LayoutCustomDialogBinding
import com.sipl.fieldwork.repository.ConnectivityRepository
import com.sipl.fieldwork.repository.MastersRepository
import com.sipl.fieldwork.utils.CustomProgressDialog
import com.sipl.fieldwork.utils.NoInternetDialog
import com.sipl.fieldwork.viewmodel.ConnectivityViewModel
import com.sipl.fieldwork.viewmodel.SplashScreenViewModel
import com.sipl.fieldwork.viewmodel.SplashScreenViewModelFactory
import com.sipl.fieldwork.webservice.ApiClient
import com.sipl.fieldwork.webservice.BaseResponse
import kotlinx.coroutines.async

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mastersViwModel: SplashScreenViewModel
    private lateinit var customProgressDialog: CustomProgressDialog
    private val connectivityViewModel: ConnectivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        customProgressDialog=CustomProgressDialog(this)
        val apiService=ApiClient.create()
        val mastersRepository=MastersRepository(apiService)
        val connectivityRepository=ConnectivityRepository(this)
        mastersViwModel=ViewModelProvider(this,SplashScreenViewModelFactory(mastersRepository,connectivityRepository)).get(SplashScreenViewModel::class.java)
       /* Handler().postDelayed(Runnable {
            val intent=Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }, 1000)*/

        binding.progressBar.setOnClickListener {
            val intent=Intent(this, LoginActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }

        lifecycleScope.async {
            mastersViwModel.mastersData.observeForever { response->

                when(response){
                    is BaseResponse.Loading->{
                        customProgressDialog.show()
                    }
                    is BaseResponse.Success-> {
                        customProgressDialog.dismiss()
                    }
                    is BaseResponse.Error -> {
                        customProgressDialog.dismiss()
                    }
                }
            }
            }
        }

    override fun onResume() {
        super.onResume()
        mastersViwModel.isOnline.observe(this) { isOnline ->
            if (isOnline) {
                Log.d("mytag","Connected")
                mastersViwModel.getMastersFromApi();
                connectivityViewModel.updateConnectionStatus(true)
                NoInternetDialog.showDialog(this, connectivityViewModel.isConnected, this)
            } else {
                Log.d("mytag","Not Connected")
                NoInternetDialog.showDialog(this, connectivityViewModel.isConnected, this)
                connectivityViewModel.updateConnectionStatus(false)
            }
        }

    }
    }
