package com.kevicsalazar.appkit_alerts

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kevicsalazar.appkit_alerts.ext.InputType
import com.kevicsalazar.appkit_alerts.ext.onAnimationEnd
import kotlinx.android.synthetic.main.alert_textinput.view.*

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class TextInputAlert(val ctx: Context) : Dialog(ctx, R.style.AppTheme_FlatDialog) {

    var titleText: String? = null
    var hintText: String? = null
    var cancelText: String? = null
    var confirmText: String? = null

    var inputType: InputType = InputType.Text

    private lateinit var mDialogView: View

    private lateinit var mModalInAnim: Animation
    private lateinit var mModalOutAnim: Animation

    private var mOnCancel: ((TextInputAlert) -> Unit)? = null
    private var mOnConfirm: ((TextInputAlert, String) -> Unit)? = null

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_textinput)
        mDialogView = window.decorView.findViewById(android.R.id.content)

        mModalInAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_in)
        mModalOutAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_out)

        mModalOutAnim.onAnimationEnd { super.dismiss() }

        with(mDialogView) {

            tvTitle.text = titleText
            etInput.hint = hintText
            btnCancel.text = cancelText ?: "Cancelar"
            btnConfirm.text = confirmText ?: "OK"

            etInput.inputType = inputType.type

            btnCancel.setOnClickListener { mOnCancel?.invoke(this@TextInputAlert) ?: this@TextInputAlert.dismiss() }
            btnConfirm.setOnClickListener { mOnConfirm?.invoke(this@TextInputAlert, etInput.text.toString()) ?: this@TextInputAlert.dismiss() }

        }

    }

    override fun onStart() {
        mDialogView.startAnimation(mModalInAnim)
    }

    override fun dismiss() {
        mDialogView.startAnimation(mModalOutAnim)
    }

    fun onCancel(listener: ((TextInputAlert) -> Unit)): TextInputAlert {
        mOnCancel = listener
        return this
    }

    fun onConfirm(listener: ((TextInputAlert, String) -> Unit)): TextInputAlert {
        mOnConfirm = listener
        return this
    }

}