package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConsejosSaludablesActivity : AppCompatActivity() {

    private lateinit var btnEntendido: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consejos_saludables)

        // Inicializar el botón
        btnEntendido = findViewById(R.id.btnEntendido)

        // Redirigir al MainActivity con el fragmento "Mi Salud"
        btnEntendido.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("fragmentToLoad", "miSalud") // Indicar el fragmento que se debe cargar
            startActivity(intent)
            finish() // Finaliza la actividad actual
        }
    }
}
