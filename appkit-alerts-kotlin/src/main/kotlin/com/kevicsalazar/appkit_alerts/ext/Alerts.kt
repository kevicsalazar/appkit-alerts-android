package com.kevicsalazar.appkit_alerts.ext

import android.content.Context
import android.support.v4.app.Fragment
import com.kevicsalazar.appkit_alerts.DownloadAlert
import com.kevicsalazar.appkit_alerts.SelectorAlert
import com.kevicsalazar.appkit_alerts.StandardAlert

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

fun Context.Alert(title: String? = null, content: String? = null, type: AlertType = AlertType.Normal, init: (StandardAlert.() -> Unit)? = null) = StandardAlert(this, type).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.InputTextAlert(title: String? = null, hint: String, inputType: InputType = InputType.Text, init: (StandardAlert.() -> Unit)? = null) = StandardAlert(this, AlertType.InputText).apply {
    this.titleText = title
    this.hintText = hint
    this.inputType = inputType
    if (init != null) init()
}

fun Context.SelectorAlert(title: String? = null, itemList: List<String>, onItemClick: (Int) -> Unit) = SelectorAlert(this).apply {
    this.titleText = title
    this.itemList = itemList
    this.onItemClick = onItemClick
}

fun Context.DownloadAlert(title: String? = null, content: String? = null) = DownloadAlert(this).apply {
    this.titleText = title
    this.contentText = content
}

fun Fragment.Alert(title: String, content: String, type: AlertType = AlertType.Normal, init: (StandardAlert.() -> Unit)? = null) = context.Alert(title, content, type, init)

fun Fragment.InputTextAlert(title: String? = null, hint: String, inputType: InputType = InputType.Text, init: (StandardAlert.() -> Unit)? = null) = context.InputTextAlert(title, hint, inputType, init)

fun Fragment.SelectorAlert(title: String? = null, itemList: List<String>, onItemClick: (Int) -> Unit) = context.SelectorAlert(title, itemList, onItemClick)

fun Fragment.DownloadAlert(title: String? = null, content: String? = null) = context.DownloadAlert(title, content)

enum class AlertType() {

    Normal,
    Warning,
    InputText,
    Progress

}

enum class InputType(val value: Int) {

    Number(2),
    Text(1)

}