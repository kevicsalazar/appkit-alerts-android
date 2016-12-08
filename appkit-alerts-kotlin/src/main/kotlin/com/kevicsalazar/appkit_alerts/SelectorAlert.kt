package com.kevicsalazar.appkit_alerts

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.alert_selector.view.*


/**
 * @author Kevin Salazar
 * @link kevicsalazar.com
 */
class SelectorAlert(context: Context) : BaseAlert(context) {

    var titleText: String? = null
    var itemList = listOf<String>()
    var onItemClick: ((Int) -> Unit)? = null

    override val layout: Int get() = R.layout.alert_selector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(mAlertView) {

            tvTitle.text = titleText

            val aa: ArrayAdapter<String>
            aa = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, itemList)
            listview.adapter = aa

            onItemClick?.let {
                listview.setOnItemClickListener { adapterView, view, i, l ->
                    onItemClick!!.invoke(i)
                    this@SelectorAlert.dismiss()
                }
            }

        }

    }

}