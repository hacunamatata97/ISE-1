package com.sun.ise.ui.common

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.sun.ise.R

class ConfirmDialog(private val positiveButtonClick: (DialogInterface, Int) -> Unit) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(context, R.style.ProfileContent)
        builder.setMessage("Are you sure to logout?")
            .setPositiveButton("Confirm", positiveButtonClick)
            .setNegativeButton("Cancel") { dialog, buttonId -> dialog.dismiss() }
        return builder.create()
    }

    override fun onStart() {
        super.onStart()
        val positiveButton = (dialog as AlertDialog).getButton(DialogInterface.BUTTON_POSITIVE)
        val negativeButton = (dialog as AlertDialog).getButton(DialogInterface.BUTTON_NEGATIVE)
        positiveButton.background = resources.getDrawable(android.R.drawable.btn_default, null)
        negativeButton.background = resources.getDrawable(android.R.drawable.btn_default, null)
    }
}
