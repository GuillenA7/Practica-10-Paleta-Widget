package com.example.prctica10_paletawidget.ui.servicios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.prctica10_paletawidget.R

import android.widget.ArrayAdapter
import com.example.prctica10_paletawidget.Servicio

class ServicioAdapter(
    context: Context,
    private val servicios: List<Servicio>
) : ArrayAdapter<Servicio>(context, 0, servicios) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_servicio, parent, false)

        val servicio = servicios[position]
        val ivServicio = view.findViewById<ImageView>(R.id.ivServicio)
        val tvServicio = view.findViewById<TextView>(R.id.tvServicio)

        ivServicio.setImageResource(servicio.imagenResId)
        tvServicio.text = servicio.nombre

        view.setOnClickListener {
            Toast.makeText(context, servicio.descripcion, Toast.LENGTH_SHORT).show()
        }

        return view
    }
}