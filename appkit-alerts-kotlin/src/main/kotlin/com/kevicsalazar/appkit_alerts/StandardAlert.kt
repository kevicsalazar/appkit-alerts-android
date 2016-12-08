package com.kevicsalazar.appkit_alerts

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kevicsalazar.appkit_alerts.ext.DialogType
import com.kevicsalazar.appkit_alerts.ext.onAnimationEnd
import kotlinx.android.synthetic.main.alert_standard.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class StandardAlert(val ctx: Context, val type: DialogType = DialogType.Normal) : Dialog(ctx, R.style.AppTheme_FlatDialog) {

    var titleText: String? = null
    var contentText: String? = null
    var cancelText: String? = null
    var confirmText: String? = null
    var cancelVisible: Int = View.GONE
    var confirmVisible: Int = View.GONE

    private lateinit var mDialogView: View

    private lateinit var mModalInAnim: Animation
    private lateinit var mModalOutAnim: Animation

    private var mOnCancel: ((StandardAlert) -> Unit)? = null
    private var mOnConfirm: ((StandardAlert) -> Unit)? = null

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_standard)
        mDialogView = window.decorView.findViewById(android.R.id.content)

        mModalInAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_in)
        mModalOutAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_out)

        mModalOutAnim.onAnimationEnd { super.dismiss() }

        with(mDialogView) {

            tvTitle.text = titleText
            tvContent.text = contentText

            btnCancel.text = cancelText
            btnConfirm.text = confirmText

            btnCancel.visibility = cancelVisible
            btnConfirm.visibility = confirmVisible

            btnCancel.setOnClickListener { mOnCancel?.invoke(this@StandardAlert) ?: this@StandardAlert.dismiss() }
            btnConfirm.setOnClickListener { mOnConfirm?.invoke(this@StandardAlert) ?: this@StandardAlert.dismiss() }

        }

    }

    override fun onStart() {
        mDialogView.startAnimation(mModalInAnim)
    }

    override fun dismiss() {
        mDialogView.startAnimation(mModalOutAnim)
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