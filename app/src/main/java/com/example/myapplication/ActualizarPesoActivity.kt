package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

class ActualizarPesoActivity : AppCompatActivity() {

    private lateinit var etNuevoPeso: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_peso)

        etNuevoPeso = findViewById(R.id.etNuevoPeso)
        btnGuardar = findViewById(R.id.btnGuardarPeso)

        val firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth.currentUser?.uid

        btnGuardar.setOnClickListener {
            val nuevoPeso = etNuevoPeso.text.toString().toDoubleOrNull()

            if (nuevoPeso != null && userId != null) {
                val database = FirebaseDatabase.getInstance()
                val userRef = database.getReference("users").child(userId)
                val historialRef = userRef.child("historial") // Referencia al historial

                // Obtener altura del usuario para recalcular IMC
                userRef.get().addOnSuccessListener { snapshot ->
                    if (snapshot.exists()) {
                        val altura = snapshot.child("height").value.toString().toDouble()
                        val imc = nuevoPeso / (altura * altura)

                        // Actualizar peso e IMC actuales
                        userRef.child("weight").setValue(nuevoPeso.toString())
                        userRef.child("imc").setValue(String.format("%.2f", imc))

                        // Guardar nuevo registro en el historial
                        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                        val nuevoRegistro = mapOf(
                            "fecha" to fechaActual,
                            "peso" to nuevoPeso,
                            "imc" to String.format("%.2f", imc)
                        )

                        historialRef.push().setValue(nuevoRegistro)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Peso actualizado y guardado en historial", Toast.LENGTH_SHORT).show()
                                finish() // Volver a la actividad anterior
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Error al actualizar el historial", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Error al obtener datos del usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, ingresa un peso válido", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
