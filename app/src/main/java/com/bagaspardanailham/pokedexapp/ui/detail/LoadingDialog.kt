package com.bagaspardanailham.pokedexapp.ui.detail

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.bagaspardanailham.pokedexapp.R

class LoadingDialog(val mActivity: Activity) {

    private lateinit var isDialog: AlertDialog


    fun startLoading() {
        val inflater = mActivity.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_dialog, null)

        val builder = AlertDialog.Builder(mActivity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun isDismiss() {
        isDialog.dismiss()
    }

}