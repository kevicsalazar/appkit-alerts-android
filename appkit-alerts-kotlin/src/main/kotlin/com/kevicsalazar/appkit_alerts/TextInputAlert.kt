package com.kevicsalazar.appkit_alerts

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.kevicsalazar.appkit_alerts.ext.InputType
import kotlinx.android.synthetic.main.alert_textinput.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class TextInputAlert(context: Context) : BaseAlert(context) {

    var titleText: String? = null
    var hintText: String? = null
    var cancelText: String? = null
    var confirmText: String? = null
    var cancelVisible: Int = View.GONE
    var confirmVisible: Int = View.GONE

    var inputType: InputType = InputType.Text

    private var mOnCancel: ((TextInputAlert) -> Unit)? = null
    private var mOnConfirm: ((TextInputAlert, String) -> Unit)? = null

    override val layout: Int get() = R.layout.alert_textinput

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mAlertView) {

            tvTitle.text = titleText
            etInput.hint = hintText

            btnCancel.text = cancelText
            btnConfirm.text = confirmText

            btnCancel.visibility = cancelVisible
            btnConfirm.visibility = confirmVisible

            etInput.inputType = inputType.type

            btnCancel.setOnClickListener { mOnCancel?.invoke(this@TextInputAlert) ?: this@TextInputAlert.dismiss() }
            btnConfirm.setOnClickListener {
                mOnConfirm?.let {
                    if (etInput.text.isNullOrBlank()) {
                        val shake = AnimationUtils.loadAnimation(context, R.anim.shake)
                        etInput.startAnimation(shake)
                    } else {
                        mOnConfirm?.invoke(this@TextInputAlert, etInput.text.toString())
                    }
                } ?: this@TextInputAlert.dismiss()
            }

        }

    }

    fun cancelButton(cancelText: String, listener: ((TextInputAlert) -> Unit)? = null) {
        this.cancelText = cancelText
        this.cancelVisible = View.VISIBLE
        this.mOnCancel = listener
    }

    fun confirmButton(confirmText: String, listener: ((TextInputAlert, String) -> Unit)? = null) {
        this.confirmText = confirmText
        this.confirmVisible = View.VISIBLE
        this.mOnConfirm = listener
    }

}