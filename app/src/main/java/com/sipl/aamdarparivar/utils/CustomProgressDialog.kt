package com.sipl.aamdarparivar.utils


import android.app.Dialog
import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.sipl.aamdarparivar.R

class CustomProgressDialog(context: Context) {
    private val dialog: CustomDialog
    private var isDialogVisible: Boolean = false
    private val cpTitle: TextView

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_custom_dialog, null)
        cpTitle = view.findViewById(R.id.cpTitle)
        setupView(view)
        dialog = CustomDialog(context)
        dialog.setContentView(view)
    }

    private fun setupView(view: View) {
        cpTitle.setTextColor(Color.WHITE)
    }

    fun show() {
        dialog.show()
        isDialogVisible = true
    }

    fun dismiss() {
        dialog.dismiss()
        isDialogVisible = false
    }

    fun isDialogVisible(): Boolean = isDialogVisible

    fun setCancelable(cancelable: Boolean) {
        dialog.setCancelable(cancelable)
    }

    fun setTitle(title: String) {
        cpTitle.text = title
    }
    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }
}
class CustomDialog(context: Context) : Dialog(context, R.style.CustomDialogTheme) {
    init {
        window?.decorView?.rootView?.setBackgroundResource(android.R.color.transparent)
        window?.decorView?.setOnApplyWindowInsetsListener { _, insets -> insets.consumeSystemWindowInsets() }
    }
}
