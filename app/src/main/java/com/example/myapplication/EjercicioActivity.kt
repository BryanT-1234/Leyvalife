package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EjerciciosActivity : AppCompatActivity() {

    private lateinit var tvEjercicio: TextView
    private lateinit var ivEjercicio: ImageView
    private lateinit var tvDetalles: TextView
    private lateinit var btnSiguiente: Button

    private val ejercicios = listOf(
        Ejercicio("Lagartijas", R.drawable.ic_lagartija, "10 repeticiones x 3 series"),
        Ejercicio("Crunch", R.drawable.ic_crunch, "15 repeticiones x 2 series"),
        Ejercicio("Sentadillas", R.drawable.ic_sentadillas, "20 repeticiones x 3 series")
    )
    private var ejercicioActual = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio)

        // Inicializar vistas
        tvEjercicio = findViewById(R.id.tvEjercicio)
        ivEjercicio = findViewById(R.id.ivEjercicio)
        tvDetalles = findViewById(R.id.tvDetalles)
        btnSiguiente = findViewById(R.id.btnSiguiente)

        // Restablecer estado diario
        restablecerEstadoDiario()

        // Mostrar el primer ejercicio
        mostrarEjercicio()

        btnSiguiente.setOnClickListener {
            if (ejercicioActual < ejercicios.size - 1) {
                ejercicioActual++
                mostrarEjercicio()
            } else {
                finalizarEjercicios()
            }
        }
    }

    private fun mostrarEjercicio() {
        val ejercicio = ejercicios[ejercicioActual]
        tvEjercicio.text = ejercicio.nombre
        ivEjercicio.setImageResource(ejercicio.imagen)
        tvDetalles.text = ejercicio.detalles

        // Cambiar el texto del botón si es el último ejercicio
        btnSiguiente.text = if (ejercicioActual == ejercicios.size - 1) "FINALIZAR" else "SIGUIENTE"
    }

    private fun finalizarEjercicios() {
        // Guardar el estado como "Completado"
        val sharedPreferences = getSharedPreferences("LeyvaLifePrefs", MODE_PRIVATE)
        val fechaHoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        sharedPreferences.edit().apply {
            putBoolean("ejerciciosCompletados", true)
            putString("lastCompletedDate", fechaHoy)
            apply()
        }

        // Regresar al apartado de ejercicios
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("navigateTo", "Ejercicios")
        startActivity(intent)
        finish()
    }

    private fun restablecerEstadoDiario() {
        val sharedPreferences = getSharedPreferences("LeyvaLifePrefs", MODE_PRIVATE)
        val fechaHoy = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val ultimaFecha = sharedPreferences.getString("lastCompletedDate", "")

        if (ultimaFecha != fechaHoy) {
            sharedPreferences.edit().apply {
                putBoolean("ejerciciosCompletados", false)
                putString("lastCompletedDate", fechaHoy)
                apply()
            }
        }
    }
}

data class Ejercicio(val nombre: String, val imagen: Int, val detalles: String)
