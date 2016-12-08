package com.kevicsalazar.appkit_alerts.ext

import android.content.Context
import android.support.v4.app.Fragment
import com.kevicsalazar.appkit_alerts.DownloadAlert
import com.kevicsalazar.appkit_alerts.StandardAlert
import com.kevicsalazar.appkit_alerts.TextInputAlert

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

fun Context.UIAlert(title: String, content: String, init: (StandardAlert.() -> Unit)?) = StandardAlert(this).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Fragment.UIAlert(title: String, content: String, init: (StandardAlert.() -> Unit)?) = StandardAlert(context).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.UIInputTextAlert(title: String, hint: String, inputType: InputType = InputType.Text, init: (TextInputAlert.() -> Unit)?) = TextInputAlert(this).apply {
    this.titleText = title
    this.hintText = hint
    this.inputType = inputType
    if (init != null) init()
}

fun Context.UIDownloadAlert(title: String, content: String, init: (DownloadAlert.() -> Unit)? = null) = DownloadAlert(this).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

enum class DialogType(val type: Int) {

    Normal(0),
    Error(1),
    Success(2),
    Warning(3),
    Custom(4),
    Progress(5)

}

enum class InputType(val type: Int) {

    Number(2),
    Text(1)

}