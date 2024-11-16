package com.example.prctica10_paletawidget

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()

            if (username == "admin" && password == "1234") {
                showDialog(
                    title = "Inicio de sesión exitoso",
                    message = "¡Bienvenido, administrador!",
                    onConfirm = {
                        startActivity(Intent(this, BottomNavigationActivity::class.java))
                        finish()
                    }
                )
            } else {
                showDialog(
                    title = "Error de autenticación",
                    message = "Credenciales incorrectas. Por favor, inténtalo de nuevo."
                )
            }
        }
    }

    private fun showDialog(title: String, message: String, onConfirm: (() -> Unit)? = null) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                onConfirm?.invoke()
                dialog.dismiss()
            }
            .create()
            .show()
    }
}