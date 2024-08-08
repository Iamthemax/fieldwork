package com.sipl.fieldwork.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

import com.sipl.fieldwork.databinding.DialogNoInternetBinding

class NoInternetDialog(context: Context) : Dialog(context) {

    private lateinit var binding: DialogNoInternetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogNoInternetBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)

        binding.btnRetry.setOnClickListener {
            // Add custom retry logic here
            dismiss()
        }
    }

    companion object {
        fun showDialog(context: Context, liveData: LiveData<Boolean>, lifecycleOwner: LifecycleOwner) {
            val dialog = NoInternetDialog(context)
            liveData.observe(lifecycleOwner, Observer { isConnected ->
                if (!isConnected) {
                    dialog.show()
                } else {
                    dialog.dismiss()
                }
            })
        }
    }
}
