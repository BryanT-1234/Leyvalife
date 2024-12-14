package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MiSaludFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mi_salud, container, false)

        val btnVerSeguimiento: Button = view.findViewById(R.id.btnVerSeguimiento)
        val btnConsejosSaludables: Button = view.findViewById(R.id.btnConsejosSaludables)

        btnVerSeguimiento.setOnClickListener {
            val intent = Intent(requireContext(), VerSeguimientoActivity::class.java)
            startActivity(intent)
        }

        btnConsejosSaludables.setOnClickListener {
            val intent = Intent(requireContext(), ConsejosSaludablesActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
