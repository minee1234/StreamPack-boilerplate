package io.github.thibaultbee.streampack.example.app.utils

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import io.github.thibaultbee.streampack.example.R

object DialogUtils {
    fun showAlertDialog(context: Context, title: String, message: String = "") {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, _: Int -> dialogInterface.dismiss() }
            .show()
    }

    fun showPermissionAlertDialog(context: Context, afterPositiveButton: () -> Unit = {}) {
        AlertDialog.Builder(context)
            .setTitle(R.string.permission)
            .setMessage(R.string.permission_not_granted)
            .setPositiveButton(android.R.string.ok) { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
                afterPositiveButton()
            }
            .show()
    }
}