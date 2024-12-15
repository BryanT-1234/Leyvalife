package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class HistorialSeguimientosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var historialAdapter: HistorialAdapter
    private val historialList = mutableListOf<HistorialItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historial_seguimientos)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewHistorial)
        historialAdapter = HistorialAdapter(historialList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = historialAdapter

        cargarHistorialDesdeFirebase()
    }

    private fun cargarHistorialDesdeFirebase() {
        val firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            val database = FirebaseDatabase.getInstance()
            val historialRef = database.getReference("users").child(userId).child("historial")

            historialRef.get().addOnSuccessListener { snapshot ->
                historialList.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val fecha = data.child("fecha").value.toString()
                        val peso = data.child("peso").value.toString().toDoubleOrNull() ?: 0.0
                        val imc = data.child("imc").value.toString().toDoubleOrNull() ?: 0.0
                        historialList.add(HistorialItem(fecha, peso, imc))
                    }
                    historialAdapter.notifyDataSetChanged()
                }
            }.addOnFailureListener {
                Toast.makeText(this, "Error al cargar el historial", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Usuario no autenticado", Toast.LENGTH_SHORT).show()
        }
    }
}

data class HistorialItem(val fecha: String, val peso: Double, val imc: Double)
