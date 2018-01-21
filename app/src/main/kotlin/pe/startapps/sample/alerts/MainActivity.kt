package pe.startapps.sample.alerts

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import pe.startapps.alerts.ext.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAlert1.setOnClickListener {
            alert("Aviso", "Se ingresó correctamente") {
                confirmButton("OK")
            }.show()
        }

        btnAlert2.setOnClickListener {
            alert("Aviso", "Seguro que desea eliminarlo", AlertType.Warning) {
                confirmButton("Sí, borrar")
            }.show()
        }

        btnAlert3.setOnClickListener {
            inputTextAlert("Ingresar", "Nombre") {
                confirmButtonWithText("OK") { _, _ ->
                    dismiss()
                }
                cancelButton("Cancelar")
            }.show()
        }

        val users = listOf("Kevin", "Kelly", "Juan", "Cristina")

        btnAlert4.setOnClickListener {
            selectorAlert("Aviso", users) {
                Log.e("Selected", users[it])
            }.show()
        }

        btnAlert5.setOnClickListener {

            val dialog = downloadAlert("En progreso", "Sincronizando usuarios")
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
            alert(content = "Sincronizando...", type = AlertType.Progress).show()
        }

        btnAlert7.setOnClickListener {
            alert("Aviso", "No hay internet") {
                iconResId = R.drawable.ic_cloud_outline_off
                confirmButton("OK")
            }.show()
        }

        btnAlert8.setOnClickListener {
            val alert = alert("Aviso", "Content") {
                confirmButton("OK")
            }
            alert.show()
            Handler().postDelayed({
                alert.titleText = "New title"
                alert.contentText = "New content"
                alert.update()
            }, 2500)
        }

    }

}
