package com.nullbyte.oneside.callback

import android.app.AlertDialog
import android.view.View

interface ViewCallback {
    fun onSuccess(view: View, dialog: AlertDialog)
}