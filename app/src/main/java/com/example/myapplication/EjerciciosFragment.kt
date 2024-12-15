package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

class EjerciciosFragment : Fragment() {

    private lateinit var tvEstado: TextView
    private lateinit var tvFechaHoy: TextView
    private lateinit var btnComenzar: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ejercicios, container, false)

        // Inicializar vistas
        tvEstado = view.findViewById(R.id.tvEstado)
        tvFechaHoy = view.findViewById(R.id.tvFechaHoy)
        btnComenzar = view.findViewById(R.id.btnComenzarEjercicios)

        // Mostrar la fecha actual con "Fecha de hoy:"
        val fechaHoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        tvFechaHoy.text = "Fecha de hoy: $fechaHoy"

        // Obtener estado guardado
        val sharedPreferences = requireContext().getSharedPreferences("LeyvaLifePrefs", Context.MODE_PRIVATE)
        val lastCompletedDate = sharedPreferences.getString("lastCompletedDate", "")
        val ejerciciosCompletados = sharedPreferences.getBoolean("ejerciciosCompletados", false)

        // Restablecer estado si la fecha cambió
        if (lastCompletedDate != fechaHoy) {
            sharedPreferences.edit().apply {
                putBoolean("ejerciciosCompletados", false)
                putString("lastCompletedDate", fechaHoy)
                apply()
            }
        }

        // Actualizar estado en pantalla
        if (ejerciciosCompletados) {
            tvEstado.text = "ESTADO : COMPLETADO"
        } else {
            tvEstado.text = "ESTADO : NO COMPLETADO"
        }

        // Configurar botón para comenzar ejercicios
        btnComenzar.setOnClickListener {
            // Redirigir a la actividad de ejercicios
            val intent = Intent(requireContext(), EjerciciosActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
