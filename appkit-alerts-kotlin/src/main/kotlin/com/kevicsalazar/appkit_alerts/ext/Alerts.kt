package com.kevicsalazar.appkit_alerts.ext

import android.content.Context
import android.support.v4.app.Fragment
import com.kevicsalazar.appkit_alerts.DownloadAlert
import com.kevicsalazar.appkit_alerts.SelectorAlert
import com.kevicsalazar.appkit_alerts.StandardAlert
import com.kevicsalazar.appkit_alerts.TextInputAlert

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

fun Context.Alert(title: String, content: String, type: DialogType = DialogType.Normal, init: (StandardAlert.() -> Unit)?) = StandardAlert(this, type).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.InputTextAlert(title: String, hint: String, inputType: InputType = InputType.Text, init: (TextInputAlert.() -> Unit)?) = TextInputAlert(this).apply {
    this.titleText = title
    this.hintText = hint
    this.inputType = inputType
    if (init != null) init()
}

fun Context.DownloadAlert(title: String, content: String, init: (DownloadAlert.() -> Unit)? = null) = DownloadAlert(this).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.SelectorAlert(title: String, itemList: List<String>, onItemClick: (Int) -> Unit) = SelectorAlert(this).apply {
    this.titleText = title
    this.itemList = itemList
    this.onItemClick = onItemClick
}

fun Fragment.Alert(title: String, content: String, type: DialogType = DialogType.Normal, init: (StandardAlert.() -> Unit)?) = context.Alert(title, content, type, init)

fun Fragment.InputTextAlert(title: String, hint: String, inputType: InputType = InputType.Text, init: (TextInputAlert.() -> Unit)?) = context.InputTextAlert(title, hint, inputType, init)

fun Fragment.DownloadAlert(title: String, content: String, init: (DownloadAlert.() -> Unit)? = null) = context.DownloadAlert(title, content, init)

fun Fragment.SelectorAlert(title: String, itemList: List<String>, onItemClick: (Int) -> Unit) = context.SelectorAlert(title, itemList, onItemClick)

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