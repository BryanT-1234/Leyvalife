package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ComidasFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_comidas, container, false)

        val btnComidaDelDia: Button = view.findViewById(R.id.btnComidaDelDia)
        val btnInfoAlimentos: Button = view.findViewById(R.id.btnInfoAlimentos)

        // Botón "Comida del día"
        btnComidaDelDia.setOnClickListener {
            val intent = Intent(activity, ComidaDelDiaActivity::class.java)
            startActivity(intent)
        }

        // Botón "Información de Alimentos"
        btnInfoAlimentos.setOnClickListener {
            val intent = Intent(activity, InfoAlimentosActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
