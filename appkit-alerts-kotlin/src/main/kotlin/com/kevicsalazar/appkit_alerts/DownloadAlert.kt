package com.kevicsalazar.appkit_alerts

import android.animation.Animator
import android.animation.ValueAnimator
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.kevicsalazar.appkit_alerts.ext.anim
import kotlinx.android.synthetic.main.alert_download.view.*
import com.kevicsalazar.appkit_alerts.ext.onAnimationEnd


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class DownloadAlert(val ctx: Context) : Dialog(ctx, R.style.AppTheme_FlatDialog) {

    var titleText: String? = null
    var contentText: String? = null

    private lateinit var mDialogView: View

    private lateinit var mModalInAnim: Animation
    private lateinit var mModalOutAnim: Animation

    private var pivotAnim: Animator? = null

    init {
        setCancelable(true)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.alert_download)
        mDialogView = window.decorView.findViewById(android.R.id.content)

        mModalInAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_in)
        mModalOutAnim = AnimationUtils.loadAnimation(ctx, R.anim.modal_out)

        mModalOutAnim.onAnimationEnd { super.dismiss() }

        with(mDialogView) {

            tvTitle.text = titleText
            tvContent.hint = contentText

            progressBar.isIndeterminate = true

            pivotAnim = ivArrow.anim("translationY", 0f, 20f) {
                repeatMode = ValueAnimator.REVERSE
                repeatCount = ValueAnimator.INFINITE
                interpolator = DecelerateInterpolator()
            }

            pivotAnim?.start()

        }

    }

    fun updateProgress(progress: Int, total: Int) {
        with(mDialogView) {
            progressBar.isIndeterminate = false
            tvPercentProgress.text = "${(progress * total) / 100}%"
            tvStatusProgress.text = "$progress/$total"
        }
    }

    override fun onStart() {
        mDialogView.startAnimation(mModalInAnim)
    }

    override fun dismiss() {
        mDialogView.startAnimation(mModalOutAnim)
        pivotAnim?.cancel()
    }

}