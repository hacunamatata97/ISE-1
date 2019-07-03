package com.sun.ise.ui.common

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View.OnClickListener
import android.view.WindowManager
import androidx.annotation.StringRes
import com.sun.ise.util.StringUtils
import kotlinx.android.synthetic.main.dialog_confirmation.*

class MaterialDialog(context: Context) : AlertDialog(context) {

    private lateinit var dialogMessage: String
    private lateinit var positiveText: String
    private lateinit var negativeText: String
    private lateinit var positiveClickListener: OnClickListener
    private lateinit var negativeClickListener: OnClickListener

    private var canDismiss = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sun.ise.R.layout.dialog_confirmation)
    }

    override fun onStart() {
        super.onStart()
        if (StringUtils.checkNotEmpty(positiveText) && positiveClickListener != null) {
            buttonConfirm.text = positiveText
            buttonConfirm.setOnClickListener(positiveClickListener)
        }
        if (StringUtils.checkNotEmpty(negativeText) && negativeClickListener != null) {
            buttonCancel.text = negativeText
            buttonCancel.setOnClickListener(negativeClickListener)
        }
        this.setCanceledOnTouchOutside(canDismiss)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.clearFlags(
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
        )
    }

    fun setMessage(message: String): MaterialDialog {
        dialogMessage = message
        return this
    }

    fun setMessage(@StringRes messageId: Int): MaterialDialog {
        setMessage(context.getString(messageId))
        return this
    }

    fun setPositiveButton(text: String, listener: OnClickListener): MaterialDialog {
        this.positiveText = text
        this.positiveClickListener = listener
        return this
    }

    fun setPositiveButton(@StringRes textId: Int, listener: OnClickListener): MaterialDialog {
        this.setPositiveButton(context.getString(textId), listener)
        return this
    }

    fun setNegativeButton(text: String, listener: OnClickListener): MaterialDialog {
        this.negativeText = text
        this.negativeClickListener = listener
        return this
    }

    fun setNegativeButton(@StringRes textId: Int, listener: OnClickListener): MaterialDialog {
        this.setNegativeButton(context.getString(textId), listener)
        return this
    }

    fun dismissOnTouchOutside(dismiss: Boolean): MaterialDialog {
        this.canDismiss = dismiss
        return this
    }
}
