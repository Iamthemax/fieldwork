package com.sipl.fieldwork.ui.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.sipl.fieldwork.R
import com.sipl.fieldwork.databinding.ActivityRegistrationBinding
import com.sipl.fieldwork.utils.MyValidator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegistrationActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        initializeData();

        binding.btnSubmit.setOnClickListener{
            if(validateFields()){

            }
        }
    }

    private fun initializeData() {

        binding.etDOB.setOnClickListener {
            showDatePicker()
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

        // Validate Ward Number
        if (MyValidator.isValidInputField(binding.etWardNo.text.toString())) {
            binding.txLayoutWardNo.error = null
            validationResults.add(true)
        } else {
            binding.txLayoutWardNo.error = getString(R.string.enter_ward_no)
            validationResults.add(false)
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
    private fun showDatePicker() {
        try {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this, { view, year, monthOfYear, dayOfMonth ->
                    val selectedDate = formatDate(dayOfMonth, monthOfYear, year)
                    binding.etDOB.setText(selectedDate)
                }, year, month, day
            )
            datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
            datePickerDialog.show()
        } catch (e: Exception) {
            Log.d("mytag", "RegistrationActivity.showDatePicker() ${e.message}", e)
            e.printStackTrace()
        }
    }

    private fun formatDate(day: Int, month: Int, year: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}