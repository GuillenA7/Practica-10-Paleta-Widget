package com.example.prctica10_paletawidget.ui.servicios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import com.example.prctica10_paletawidget.R
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
        val rbServicio = view.findViewById<RatingBar>(R.id.rbServicio)

        ivServicio.setImageResource(servicio.imagenResId)
        tvServicio.text = servicio.nombre
        rbServicio.rating = servicio.calificacion

        rbServicio.setOnRatingBarChangeListener { _, rating, _ ->
            servicio.calificacion = rating
            showDialog(
                context = context,
                title = "Calificación del servicio",
                message = "Has calificado '${servicio.nombre}' con $rating estrellas."
            )
        }

        return view
    }

    private fun showDialog(context: Context, title: String, message: String) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Aceptar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}