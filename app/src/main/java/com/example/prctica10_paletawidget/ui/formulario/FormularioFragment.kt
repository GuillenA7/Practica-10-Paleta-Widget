package com.example.prctica10_paletawidget.ui.formulario

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
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

        var fechaSeleccionada = ""
        calendarView.setOnDateChangeListener { _, year, month, day ->
            fechaSeleccionada = "$day/${month + 1}/$year"
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val ubicacion = etUbicacion.text.toString()

            if (nombre.isNotEmpty() && ubicacion.isNotEmpty() && fechaSeleccionada.isNotEmpty()) {
                listaBalnearios.add(Balneario(nombre, ubicacion, fechaSeleccionada))
                Toast.makeText(requireContext(), "Balneario registrado", Toast.LENGTH_SHORT).show()
                etNombre.text.clear()
                etUbicacion.text.clear()
            } else {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}