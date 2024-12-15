package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleComidaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_comida)

        val tvComidaTitulo: TextView = findViewById(R.id.tvComidaTitulo)
        val ivComida: ImageView = findViewById(R.id.ivComida)
        val tvComidaDescripcion: TextView = findViewById(R.id.tvComidaDescripcion)
        val tvCalorias: TextView = findViewById(R.id.tvCalorias)
        val tvProteinas: TextView = findViewById(R.id.tvProteinas)
        val tvCarbohidratos: TextView = findViewById(R.id.tvCarbohidratos)
        val tvGrasas: TextView = findViewById(R.id.tvGrasas)

        val comida = intent.getStringExtra("comida")

        when (comida) {
            "Desayuno" -> {
                tvComidaTitulo.text = "Pan con palta y atún"
                ivComida.setImageResource(R.drawable.ic_desayuno)
                tvComidaDescripcion.text = "El pan con palta y atún es una opción nutritiva, ideal para un desayuno o merienda balanceada. Combina carbohidratos complejos del pan, grasas saludables de la palta y proteínas magras del atún, ofreciendo energía sostenida y beneficios para el corazón. Por cada porción promedio (1 pan integral, 50 g de palta y 80 g de atún en agua):"
                tvCalorias.text = "Calorías: 320 kcal"
                tvProteinas.text = "Proteínas: 18g"
                tvCarbohidratos.text = "Carbohidratos: 25g"
                tvGrasas.text = "Grasas: 14g"
            }
            "Almuerzo" -> {
                tvComidaTitulo.text = "Ensalada de pollo frito"
                ivComida.setImageResource(R.drawable.ic_almuerzo)
                tvComidaDescripcion.text = "Esta ensalada combina pollo frito, verduras frescas y, en algunos casos, aderezos, lo que la convierte en un plato con un equilibrio entre sabor y nutrientes. Aunque el pollo frito aumenta el contenido calórico y de grasas, las verduras aportan fibra y micronutrientes esenciales. Por una porción promedio (100 g de pollo frito, lechuga, tomate, zanahoria y aderezo):"
                tvCalorias.text = "Calorías: 290 kcal"
                tvProteinas.text = "Proteínas: 22g"
                tvCarbohidratos.text = "Carbohidratos: 10g"
                tvGrasas.text = "Grasas: 17g"
            }
            "Cena" -> {
                tvComidaTitulo.text = "Sopa de pollo"
                ivComida.setImageResource(R.drawable.ic_cena)
                tvComidaDescripcion.text = "La sopa de pollo es un platillo ligero y reconfortante, ideal para hidratarse y reponer energías. El caldo y las verduras aportan vitaminas y minerales, mientras que el pollo proporciona proteínas de alta calidad. Por una porción promedio (250 ml de sopa con 80 g de pollo, zanahoria, papa y apio):"
                tvCalorias.text = "Calorías: 200 kcal"
                tvProteinas.text = "Proteínas: 12g"
                tvCarbohidratos.text = "Carbohidratos: 12g"
                tvGrasas.text = "Grasas: 5g"
            }
        }
    }
}
