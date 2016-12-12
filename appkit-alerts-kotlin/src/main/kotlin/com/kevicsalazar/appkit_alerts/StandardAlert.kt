package com.kevicsalazar.appkit_alerts

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import com.kevicsalazar.appkit_alerts.ext.AlertType
import com.kevicsalazar.appkit_alerts.ext.InputType
import kotlinx.android.synthetic.main.alert_standard.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class StandardAlert(context: Context, val type: AlertType) : BaseAlert(context) {

    var titleText: String? = null
    var contentText: String? = null
    var iconResId: Int? = null

    var hintText: String? = null
    var inputType = InputType.Text

    private var cancelText: String? = null
    private var confirmText: String? = null

    private var mOnCancel: ((StandardAlert) -> Unit)? = null
    private var mOnConfirm: ((StandardAlert) -> Unit)? = null
    private var mOnConfirmWithText: ((StandardAlert, String) -> Unit)? = null

    override val layout: Int get() = R.layout.alert_standard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mAlertView) {

            titleText?.let {
                tvTitle.text = it
                tvTitle.visibility = View.VISIBLE
            }

            contentText?.let {
                tvContent.text = it
                tvContent.visibility = View.VISIBLE
            }

            cancelText?.let {
                btnCancel.text = it
                btnCancel.visibility = View.VISIBLE
                btnCancel.setOnClickListener { mOnCancel?.invoke(this@StandardAlert) ?: this@StandardAlert.dismiss() }
            }

            confirmText?.let {
                btnConfirm.text = it
                btnConfirm.visibility = View.VISIBLE
                btnConfirm.setOnClickListener {
                    mOnConfirm?.invoke(this@StandardAlert) ?: mOnConfirmWithText?.let {
                        if (etInput.text.isNullOrBlank()) {
                            val shake = AnimationUtils.loadAnimation(context, R.anim.shake)
                            etInput.startAnimation(shake)
                        } else {
                            mOnConfirmWithText?.invoke(this@StandardAlert, etInput.text.toString())
                        }
                    } ?: this@StandardAlert.dismiss()
                }
            }

            iconResId?.let {
                ivIcon.setImageResource(it)
                ivIcon.visibility = View.VISIBLE
            }

            when (type) {
                AlertType.Normal    -> btnConfirm.setBackgroundResource(R.drawable.bg_btn_confirm)
                AlertType.Warning   -> btnConfirm.setBackgroundResource(R.drawable.bg_btn_warning)
                AlertType.InputText -> {
                    etInput.hint = hintText
                    etInput.inputType = inputType.value
                    etInput.visibility = View.VISIBLE
                }
                AlertType.Progress  -> {
                    progressBar.visibility = View.VISIBLE
                    ivIcon.visibility = View.GONE
                    btnCancel.visibility = View.GONE
                    btnConfirm.visibility = View.GONE
                }
            }

        }

    }

    fun cancelButton(cancelText: String, listener: ((StandardAlert) -> Unit)? = null) {
        this.cancelText = cancelText
        this.mOnCancel = listener
    }

    fun confirmButton(confirmText: String, listener: ((StandardAlert) -> Unit)? = null) {
        this.confirmText = confirmText
        this.mOnConfirm = listener
    }

    fun confirmButtonWithText(confirmText: String, listener: ((StandardAlert, String) -> Unit)? = null) {
        this.confirmText = confirmText
        this.mOnConfirmWithText = listener
    }

}