package com.kevicsalazar.sample.appkit_alerts

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.kevicsalazar.appkit_alerts.ext.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAlert1.setOnClickListener {
            Alert("Aviso", "Se ingresó correctamente") {
                confirmButton("OK")
            }.show()
        }

        btnAlert2.setOnClickListener {
            Alert("Aviso", "Seguro que desea eliminarlo", AlertType.Warning) {
                confirmButton("Sí, borrar")
            }.show()
        }

        btnAlert3.setOnClickListener {
            InputTextAlert("Ingresar", "Nombre") {
                confirmButtonWithText("OK") { dialog, input ->
                    dismiss()
                }
                cancelButton("Cancelar")
            }.show()
        }

        val users = listOf("Kevin", "Kelly", "Juan", "Cristina")

        btnAlert4.setOnClickListener {
            SelectorAlert("Aviso", users) {
                Log.e("Selected", users[it])
            }.show()
        }

        btnAlert5.setOnClickListener {

            val dialog = DownloadAlert("En progreso", "Sincronizando usuarios")
            dialog.show()

            Handler().postDelayed({
                dialog.updateProgress(1, 50)
            }, 1000)

            Handler().postDelayed({
                dialog.updateProgress(2, 50)
            }, 2000)

            Handler().postDelayed({
                dialog.updateProgress(3, 50)
            }, 3000)

            Handler().postDelayed({
                dialog.updateProgress(49, 50)
            }, 4000)

            Handler().postDelayed({
                dialog.updateProgress(50, 50)
                dialog.dismiss()
            }, 5000)

        }

        btnAlert6.setOnClickListener {
            Alert(content = "Sincronizando...", type = AlertType.Progress).show()
        }

        btnAlert7.setOnClickListener {
            Alert("Aviso", "No hay internet") {
                iconResId = R.drawable.ic_cloud_outline_off
                confirmButton("OK")
            }.show()
        }

    }


}
