package com.example.oneside.utilities;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.oneside.callback.ViewCallback;

import java.util.Objects;

public class VariableAndMethodUtility {
    public static void customDialog(Context context, int layout, ViewCallback viewCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(layout, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        viewCallback.onSuccess(view, dialog);
        //dialog.show();
    }

    public static void customClosableDialog(Context context, int layout, ViewCallback viewCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(layout, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(true);
        viewCallback.onSuccess(view, dialog);
        //dialog.show();
    }
}
