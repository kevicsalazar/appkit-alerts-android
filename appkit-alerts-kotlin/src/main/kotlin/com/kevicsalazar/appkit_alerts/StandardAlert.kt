package com.kevicsalazar.appkit_alerts

import android.content.Context
import android.os.Bundle
import android.view.View
import com.kevicsalazar.appkit_alerts.ext.DialogType
import kotlinx.android.synthetic.main.alert_standard.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class StandardAlert(context: Context, val type: DialogType) : BaseAlert(context) {

    var titleText: String? = null
    var contentText: String? = null

    private var cancelText: String? = null
    private var confirmText: String? = null
    private var cancelVisible: Int = View.GONE
    private var confirmVisible: Int = View.GONE

    private var mOnCancel: ((StandardAlert) -> Unit)? = null
    private var mOnConfirm: ((StandardAlert) -> Unit)? = null

    override val layout: Int get() = R.layout.alert_standard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mAlertView) {

            tvTitle.text = titleText
            tvContent.text = contentText

            btnCancel.text = cancelText
            btnConfirm.text = confirmText

            btnCancel.visibility = cancelVisible
            btnConfirm.visibility = confirmVisible

            if (type == DialogType.Warning) btnConfirm.setBackgroundResource(R.drawable.bg_btn_warning)

            btnCancel.setOnClickListener { mOnCancel?.invoke(this@StandardAlert) ?: this@StandardAlert.dismiss() }
            btnConfirm.setOnClickListener { mOnConfirm?.invoke(this@StandardAlert) ?: this@StandardAlert.dismiss() }

        }

    }

    fun cancelButton(cancelText: String, listener: ((StandardAlert) -> Unit)? = null) {
        this.cancelText = cancelText
        this.cancelVisible = View.VISIBLE
        this.mOnCancel = listener
    }

    fun confirmButton(confirmText: String, listener: ((StandardAlert) -> Unit)? = null) {
        this.confirmText = confirmText
        this.confirmVisible = View.VISIBLE
        this.mOnConfirm = listener
    }

}