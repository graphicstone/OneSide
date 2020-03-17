package com.example.oneside.utilities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.oneside.callback.ViewCallback
import com.google.android.material.snackbar.Snackbar

object VariableAndMethodUtility {
    fun customDialog(
        context: Context?,
        layout: Int,
        viewCallback: ViewCallback
    ) {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(layout, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        viewCallback.onSuccess(view, dialog)
    }

    fun customClosableDialog(
        context: Context?,
        layout: Int,
        viewCallback: ViewCallback
    ) {
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(layout, null)
        builder.setView(view)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(true)
        viewCallback.onSuccess(view, dialog)
    }

    fun showSnackbar(context: Context, message: String?) {
        Snackbar.make(
                (context as Activity).findViewById(android.R.id.content),
                message!!,
                Snackbar.LENGTH_LONG
            )
            .setAction("Action", null)
            .show()
    }
}
