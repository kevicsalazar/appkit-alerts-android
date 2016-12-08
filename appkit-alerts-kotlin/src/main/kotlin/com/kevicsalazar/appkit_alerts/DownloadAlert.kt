package com.kevicsalazar.appkit_alerts

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.alert_download.view.*
import android.animation.AnimatorSet
import android.animation.AnimatorInflater


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class DownloadAlert(context: Context) : BaseAlert(context) {

    var titleText: String? = null
    var contentText: String? = null

    override val layout: Int get() = R.layout.alert_download

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mAlertView) {

            tvTitle.text = titleText
            tvContent.hint = contentText

            progressBar.isIndeterminate = true

            val set = AnimatorInflater.loadAnimator(context, R.animator.cloud_dowload) as AnimatorSet
            set.setTarget(ivArrow)
            set.start()

        }

    }

    fun updateProgress(progress: Int, total: Int) {
        with(mAlertView) {
            progressBar.isIndeterminate = false
            progressBar.progress = progress
            progressBar.max = total
            tvPercentProgress.text = "${(progress * 100) / total}%"
            tvStatusProgress.text = "$progress/$total"
        }
    }

}