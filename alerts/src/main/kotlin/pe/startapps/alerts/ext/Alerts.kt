package pe.startapps.alerts.ext

import android.content.Context
import android.support.v4.app.Fragment
import pe.startapps.alerts.DownloadAlert
import pe.startapps.alerts.SelectorAlert
import pe.startapps.alerts.StandardAlert

/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */

fun Context.alert(title: String? = null, content: String? = null, type: AlertType = AlertType.Normal, init: (StandardAlert.() -> Unit)? = null) = StandardAlert(this, type).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.progressAlert(title: String? = null, content: String? = null, init: (StandardAlert.() -> Unit)? = null) = StandardAlert(this, AlertType.Progress).apply {
    this.titleText = title
    this.contentText = content
    if (init != null) init()
}

fun Context.inputTextAlert(title: String? = null, hint: String, inputType: InputType = InputType.Text, init: (StandardAlert.() -> Unit)? = null) = StandardAlert(this, AlertType.InputText).apply {
    this.titleText = title
    this.hintText = hint
    this.inputType = inputType
    if (init != null) init()
}

fun Context.selectorAlert(title: String? = null, itemList: List<String>, onItemClick: (Int) -> Unit) = SelectorAlert(this).apply {
    this.titleText = title
    this.itemList = itemList
    this.onItemClick = onItemClick
}

fun Context.downloadAlert(title: String? = null, content: String? = null) = DownloadAlert(this).apply {
    this.titleText = title
    this.contentText = content
}

fun Fragment.Alert(title: String, content: String, type: AlertType = AlertType.Normal, init: (StandardAlert.() -> Unit)? = null) = context?.alert(title, content, type, init)

fun Fragment.InputTextAlert(title: String? = null, hint: String, inputType: InputType = InputType.Text, init: (StandardAlert.() -> Unit)? = null) = context?.inputTextAlert(title, hint, inputType, init)

fun Fragment.SelectorAlert(title: String? = null, itemList: List<String>, onItemClick: (Int) -> Unit) = context?.selectorAlert(title, itemList, onItemClick)

fun Fragment.DownloadAlert(title: String? = null, content: String? = null) = context?.downloadAlert(title, content)

enum class AlertType {

    Normal,
    Warning,
    InputText,
    Progress

}

enum class InputType(val value: Int) {

    Number(2),
    Text(1)

}