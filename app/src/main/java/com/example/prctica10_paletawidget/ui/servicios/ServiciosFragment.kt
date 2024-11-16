package com.example.prctica10_paletawidget.ui.servicios

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.VideoView
import android.widget.Button
import android.widget.RatingBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.prctica10_paletawidget.R
import com.example.prctica10_paletawidget.Servicio

class ServiciosFragment : Fragment(R.layout.fragment_servicios) {

    private val servicios = listOf(
        Servicio("Albercas para adultos", "Disfruta de nuestra alberca exclusiva para adultos.", R.drawable.alberca),
        Servicio("Toboganes gigantes", "Deslízate en los mejores toboganes.", R.drawable.toboganes),
        Servicio("Áreas de comida", "Prueba nuestra variada oferta gastronómica.", R.drawable.comida),
        Servicio("Zonas de descanso", "Relájate en nuestras cómodas áreas de descanso.", R.drawable.descanso)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_servicios, container, false)

        val lvServicios: ListView = view.findViewById(R.id.lvServicios)
        val videoPromocional: VideoView = view.findViewById(R.id.videoPromocional)
        val btnPlayVideo: Button = view.findViewById(R.id.btnPlayVideo)
        val rbVideo: RatingBar = view.findViewById(R.id.rbVideo)

        // Configurar adaptador personalizado
        val adapter = ServicioAdapter(requireContext(), servicios)
        lvServicios.adapter = adapter

        // Configurar VideoView
        val videoPath = "android.resource://${requireActivity().packageName}/${R.raw.promocional}"
        videoPromocional.setVideoURI(Uri.parse(videoPath))

        btnPlayVideo.setOnClickListener {
            if (!videoPromocional.isPlaying) {
                videoPromocional.start()
            }
        }

        // Configurar RatingBar para el video
        rbVideo.setOnRatingBarChangeListener { _, rating, _ ->
            showDialog(
                title = "Calificación del video",
                message = "Has calificado el video promocional con $rating estrellas."
            )
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