package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ComidaDelDiaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_del_dia)

        val btnDesayuno: Button = findViewById(R.id.btnDesayuno)
        val btnAlmuerzo: Button = findViewById(R.id.btnAlmuerzo)
        val btnCena: Button = findViewById(R.id.btnCena)

        btnDesayuno.setOnClickListener {
            startActivity(Intent(this, DetalleComidaActivity::class.java).putExtra("comida", "Desayuno"))
        }

        btnAlmuerzo.setOnClickListener {
            startActivity(Intent(this, DetalleComidaActivity::class.java).putExtra("comida", "Almuerzo"))
        }

        btnCena.setOnClickListener {
            startActivity(Intent(this, DetalleComidaActivity::class.java).putExtra("comida", "Cena"))
        }
    }
}
