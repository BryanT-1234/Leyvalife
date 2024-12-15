package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class InfoAlimentosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_alimentos)

        // Inicializar botones
        val btnManzana: Button = findViewById(R.id.btnManzana)
        val btnPollo: Button = findViewById(R.id.btnPollo)
        val btnLeche: Button = findViewById(R.id.btnLeche)
        val btnBrocoli: Button = findViewById(R.id.btnBrocoli)
        val btnQuinua: Button = findViewById(R.id.btnQuinua)

        // Configurar los listeners
        btnManzana.setOnClickListener { abrirDetalleAlimento("Manzana") }
        btnPollo.setOnClickListener { abrirDetalleAlimento("Pollo") }
        btnLeche.setOnClickListener { abrirDetalleAlimento("Leche") }
        btnBrocoli.setOnClickListener { abrirDetalleAlimento("Brócoli") }
        btnQuinua.setOnClickListener { abrirDetalleAlimento("Quinua") }
    }

    // Función para abrir la actividad de detalle de alimentos
    private fun abrirDetalleAlimento(alimento: String) {
        val intent = Intent(this, DetalleAlimentoActivity::class.java)
        intent.putExtra("alimento", alimento)
        startActivity(intent)
    }
}
