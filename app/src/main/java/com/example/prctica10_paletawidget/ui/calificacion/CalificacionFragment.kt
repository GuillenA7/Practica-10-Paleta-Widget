package com.example.prctica10_paletawidget.ui.calificacion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.prctica10_paletawidget.R

class CalificacionFragment : Fragment(R.layout.fragment_calificacion) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_calificacion, container, false)

        val rbCalificacion: RatingBar = view.findViewById(R.id.rbCalificacion)
        val btnEnviar: Button = view.findViewById(R.id.btnEnviarCalificacion)
        val tvMensaje: TextView = view.findViewById(R.id.tvCalificacionMensaje)

        btnEnviar.setOnClickListener {
            val calificacion = rbCalificacion.rating
            val mensaje = when {
                calificacion < 2 -> "Servicio deficiente"
                calificacion < 4 -> "Servicio bueno"
                else -> "¡Servicio excelente!"
            }

            tvMensaje.text = mensaje
            Toast.makeText(requireContext(), "Calificación enviada: $mensaje", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}