package com.example.prctica10_paletawidget.ui.formulario

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.prctica10_paletawidget.Balneario
import com.example.prctica10_paletawidget.R

class FormularioFragment : Fragment(R.layout.fragment_formulario) {

    private val listaBalnearios = mutableListOf<Balneario>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_formulario, container, false)

        val btnGuardar: Button = view.findViewById(R.id.btnGuardar)
        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        val etNombre: EditText = view.findViewById(R.id.etNombreBalneario)
        val etUbicacion: EditText = view.findViewById(R.id.etUbicacion)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)

        var fechaSeleccionada = ""
        calendarView.setOnDateChangeListener { _, year, month, day ->
            fechaSeleccionada = "$day/${month + 1}/$year"
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val ubicacion = etUbicacion.text.toString()

            if (nombre.isNotEmpty() && ubicacion.isNotEmpty() && fechaSeleccionada.isNotEmpty()) {
                // Mostrar barra de progreso
                progressBar.visibility = View.VISIBLE

                // Simular proceso de guardado con un delay
                Handler(Looper.getMainLooper()).postDelayed({
                    listaBalnearios.add(Balneario(nombre, ubicacion, fechaSeleccionada))
                    progressBar.visibility = View.GONE

                    // Mostrar cuadro de diálogo de éxito
                    showDialog(
                        title = "Registro exitoso",
                        message = "El balneario '$nombre' se ha registrado con éxito."
                    ) {
                        // Limpiar campos después de cerrar el diálogo
                        etNombre.text.clear()
                        etUbicacion.text.clear()
                    }
                }, 2000) // 2 segundos de delay
            } else {
                // Mostrar cuadro de diálogo de error
                showDialog(
                    title = "Error",
                    message = "Por favor, completa todos los campos antes de continuar."
                )
            }
        }

        return view
    }

    private fun showDialog(title: String, message: String, onConfirm: (() -> Unit)? = null) {
        AlertDialog.Builder(requireContext())
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