package com.example.myapplication.ui

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.TextView
import com.example.myapplication.R

class LoadingDialog(private val context: Context?) {

    lateinit var dialog: Dialog

    fun showProgressDialog(title: String) {
        dialog = Dialog(context!!)
        dialog.setContentView(R.layout.loading_dialog_view)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val titleText = dialog.findViewById<TextView>(R.id.mTextViewTitle)
        titleText.text = title
        dialog.create()
        dialog.show()
    }

    fun hideProgressDialog() {
        dialog.dismiss()
    }
}