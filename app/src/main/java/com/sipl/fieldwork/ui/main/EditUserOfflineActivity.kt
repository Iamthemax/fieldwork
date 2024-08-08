package com.sipl.fieldwork.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sipl.fieldwork.R
import com.sipl.fieldwork.databinding.ActivityEditUserOfflineBinding
import com.sipl.fieldwork.utils.MyValidator

class EditUserOfflineActivity : AppCompatActivity() {
    lateinit var binding:ActivityEditUserOfflineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityEditUserOfflineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun validateFields(): Boolean {
        val validationResults = ArrayList<Boolean>()

        // Validate First Name
        if (MyValidator.isValidName(binding.etFirstName.text.toString())) {
            binding.txLayoutFirstName.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutFirstName.error = getString(R.string.enter_first_name)
            validationResults.add(false)
        }

        // Validate Middle Name
        if (MyValidator.isValidName(binding.etMiddleName.text.toString())) {
            binding.txLayoutMiddleName.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutMiddleName.error = getString(R.string.enter_middle_name)
            validationResults.add(false)
        }

        // Validate Last Name
        if (MyValidator.isValidName(binding.etLastName.text.toString())) {
            binding.txLayoutLastName.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutLastName.error = getString(R.string.enter_last_name)
            validationResults.add(false)
        }

        // Validate Date of Birth
        if (binding.etDOB.enoughToFilter()) {
            binding.txLayoutDOB.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutDOB.error = getString(R.string.select_date_of_birth)
            validationResults.add(false)
        }

        // Validate Gender
        if (binding.etGender.enoughToFilter()) {
            binding.txLayoutGender.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutGender.error = getString(R.string.select_gender)
            validationResults.add(false)
        }

        // Validate Caste
        if (binding.etCaste.enoughToFilter()) {
            binding.txLayoutCaste.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutCaste.error = getString(R.string.select_caste)
            validationResults.add(false)
        }

        // Validate Mobile Number
        if (MyValidator.isValidMobileNumber(binding.etMobile.text.toString())) {
            binding.txLayoutMobile.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutMobile.error = getString(R.string.mobile_number)
            validationResults.add(false)
        }

        // Validate Voter ID
        if (MyValidator.isValidInputField(binding.etVoterID.text.toString())) {
            binding.txLayoutVoterID.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutVoterID.error = getString(R.string.enter_voter_id)
            validationResults.add(false)
        }

        // Validate Taluka
        if (binding.etTaluka.enoughToFilter()) {
            binding.txLayoutTaluka.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutTaluka.error = getString(R.string.select_taluka)
            validationResults.add(false)
        }

        // Validate Village
        if (binding.etVillage.enoughToFilter()) {
            binding.txLayoutVillage.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutVillage.error = getString(R.string.select_village)
            validationResults.add(false)
        }

        if(MyValidator.isValidInputField(binding.etWardNo.text.toString()))
        {
            // Validate Ward Number
            if (MyValidator.isValidInputField(binding.etWardNo.text.toString())) {
                binding.txLayoutWardNo.error = null
                validationResults.add(true)
            } else {
                binding.txLayoutWardNo.error = getString(R.string.enter_ward_no)
                validationResults.add(false)
            }
        }
        if(MyValidator.isValidInputField(binding.etOtherCaste.text.toString()))
        {
            // Validate Ward Number
            if (MyValidator.isValidInputField(binding.etOtherCaste.text.toString())) {
                binding.etOtherCaste.error = null
                validationResults.add(true)
            } else {
                binding.etOtherCaste.error = getString(R.string.enter_caste_name)
                validationResults.add(false)
            }
        }



        // Validate Address
        if (MyValidator.isValidInputField(binding.etAddress.text.toString())) {
            binding.txLayoutAddress.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutAddress.error = getString(R.string.enter_address)
            validationResults.add(false)
        }

        return !validationResults.contains(false)
    }
}