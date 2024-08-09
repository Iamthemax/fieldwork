package com.sipl.aamdarparivar.ui.startup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.lifecycleScope
import com.sipl.aamdarparivar.R
import com.sipl.aamdarparivar.database.AppDatabase
import com.sipl.aamdarparivar.database.dao.AreaDao
import com.sipl.aamdarparivar.database.dao.CasteDao
import com.sipl.aamdarparivar.database.dao.GenderDao
import com.sipl.aamdarparivar.databinding.ActivitySplashBinding
import com.sipl.aamdarparivar.repository.AreaRepository
import com.sipl.aamdarparivar.repository.CasteRepository
import com.sipl.aamdarparivar.repository.ConnectivityRepository
import com.sipl.aamdarparivar.repository.GenderRepository
import com.sipl.aamdarparivar.repository.MastersRepository
import com.sipl.aamdarparivar.utils.CustomProgressDialog
import com.sipl.aamdarparivar.utils.NoInternetDialog
import com.sipl.aamdarparivar.viewmodel.AreaViewModel
import com.sipl.aamdarparivar.viewmodel.AreaViewModelFactory
import com.sipl.aamdarparivar.viewmodel.CasteViewModel
import com.sipl.aamdarparivar.viewmodel.CasteViewModelFactory
import com.sipl.aamdarparivar.viewmodel.ConnectivityViewModel
import com.sipl.aamdarparivar.viewmodel.GenderViewModel
import com.sipl.aamdarparivar.viewmodel.GenderViewModelFactory
import com.sipl.aamdarparivar.viewmodel.SplashScreenViewModel
import com.sipl.aamdarparivar.viewmodel.SplashScreenViewModelFactory
import com.sipl.aamdarparivar.webservice.ApiClient
import com.sipl.aamdarparivar.webservice.BaseResponse
import kotlinx.coroutines.async

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mastersViwModel: SplashScreenViewModel
    private lateinit var areaViewModel: AreaViewModel
    private lateinit var genderViewModel: GenderViewModel
    private lateinit var casteViewModel: CasteViewModel
    private lateinit var customProgressDialog: CustomProgressDialog
    private lateinit var areaDao: AreaDao
    private lateinit var genderDao: GenderDao
    private lateinit var casteDao: CasteDao
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
        areaDao= AppDatabase.getDatabase(this).areaDao()
        casteDao= AppDatabase.getDatabase(this).casteDao()
        genderDao= AppDatabase.getDatabase(this).genderDao()
        val areaRepository=AreaRepository(areaDao)
        val casteRepository=CasteRepository(casteDao)
        val genderRepository=GenderRepository(genderDao)
        areaViewModel=ViewModelProvider(this,AreaViewModelFactory(areaRepository)).get(AreaViewModel::class.java)
        genderViewModel=ViewModelProvider(this,GenderViewModelFactory(genderRepository)).get(GenderViewModel::class.java)
        casteViewModel=ViewModelProvider(this,CasteViewModelFactory(casteRepository)).get(CasteViewModel::class.java)
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

                        if(areaDao.getAllAreaCount().value!! >0)
                        {

                        }

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
