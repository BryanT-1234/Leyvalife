package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class VerSeguimientoActivity : AppCompatActivity() {

    private lateinit var tvNombre: TextView
    private lateinit var tvEdad: TextView
    private lateinit var tvTalla: TextView
    private lateinit var tvPeso: TextView
    private lateinit var tvImcActual: TextView
    private lateinit var tvImcMeta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_seguimiento)

        // Inicializar los TextViews
        tvNombre = findViewById(R.id.tvNombre)
        tvEdad = findViewById(R.id.tvEdad)
        tvTalla = findViewById(R.id.tvTalla)
        tvPeso = findViewById(R.id.tvPeso)
        tvImcActual = findViewById(R.id.tvImcActual)
        tvImcMeta = findViewById(R.id.tvImcMeta)

        // Obtener los datos del usuario desde Firebase
        val firebaseAuth = FirebaseAuth.getInstance()
        val userId = firebaseAuth.currentUser?.uid

        if (userId != null) {
            val database = FirebaseDatabase.getInstance()
            val userRef = database.getReference("users").child(userId)

            userRef.get().addOnSuccessListener { snapshot ->
                if (snapshot.exists()) {
                    val nombre = snapshot.child("name").value.toString()
                    val edad = snapshot.child("age").value.toString()
                    val talla = snapshot.child("height").value.toString()
                    val peso = snapshot.child("weight").value.toString()
                    val imc = snapshot.child("imc").value.toString()

                    // Mostrar los datos en los TextViews
                    tvNombre.text = "Nombre: $nombre"
                    tvEdad.text = "Edad: $edad años"
                    tvTalla.text = "Talla: $talla m"
                    tvPeso.text = "Peso: $peso kg"
                    tvImcActual.text = "IMC Actual: ${String.format("%.2f", imc.toDouble())}"
                    tvImcMeta.text = "IMC Meta: 23.0"
                } else {
                    tvNombre.text = "Error: No se encontraron datos del usuario."
                }
            }.addOnFailureListener {
                tvNombre.text = "Error al cargar los datos."
            }
        } else {
            tvNombre.text = "Usuario no autenticado."
        }
    }
}
