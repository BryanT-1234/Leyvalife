package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleAlimentoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alimento)

        // Inicializar vistas
        val tvTitulo: TextView = findViewById(R.id.tvAlimentoTitulo)
        val ivImagen: ImageView = findViewById(R.id.ivAlimentoImagen)
        val tvDescripcion: TextView = findViewById(R.id.tvAlimentoDescripcion)

        // Obtener el nombre del alimento desde el Intent
        val alimento = intent.getStringExtra("alimento")

        // Asignar detalles en función del alimento
        when (alimento) {
            "Manzana" -> {
                tvTitulo.text = "Manzana"
                ivImagen.setImageResource(R.drawable.ic_manzana) // Imagen referencial
                tvDescripcion.text = "La manzana es una fruta rica en nutrientes y baja en calorías, ideal para una dieta equilibrada. Su contenido alto en fibra ayuda a mejorar la digestión y controlar el colesterol. Además, contiene antioxidantes como la vitamina C, que fortalece el sistema inmunológico. Por cada 100 gramos, aporta aproximadamente:\n" +
                        "\n" +
                        "Calorías: 52\n" +
                        "Carbohidratos: 14 g\n" +
                        "Fibra: 2.4 g\n" +
                        "Azúcares: 10 g\n" +
                        "Proteínas: 0.3 g\n" +
                        "Grasas: 0.2 g"
            }
            "Pollo" -> {
                tvTitulo.text = "Pollo"
                ivImagen.setImageResource(R.drawable.ic_pollo)
                tvDescripcion.text = "El pollo es una fuente magra de proteínas, excelente para el desarrollo muscular y la recuperación. Es bajo en grasas y contiene vitaminas del grupo B y minerales como fósforo y selenio. Por cada 100 gramos de pechuga de pollo cocida:\n" +
                        "\n" +
                        "Calorías: 165\n" +
                        "Proteínas: 31 g\n" +
                        "Grasas: 3.6 g\n" +
                        "Carbohidratos: 0 g"
            }
            "Leche" -> {
                tvTitulo.text = "Leche"
                ivImagen.setImageResource(R.drawable.ic_leche)
                tvDescripcion.text = "La leche es un alimento completo, rico en calcio, vitamina D y proteínas, esenciales para la salud ósea y muscular. También contiene grasas saludables y carbohidratos en forma de lactosa. Por cada 100 ml de leche entera:\n" +
                        "\n" +
                        "Calorías: 61\n" +
                        "Proteínas: 3.2 g\n" +
                        "Grasas: 3.3 g\n" +
                        "Carbohidratos: 4.8 g"
            }
            "Brócoli" -> {
                tvTitulo.text = "Brócoli"
                ivImagen.setImageResource(R.drawable.ic_brocoli)
                tvDescripcion.text = "El brócoli es un vegetal crucífero bajo en calorías, pero cargado de vitaminas A, C, K y antioxidantes. Es una excelente fuente de fibra, calcio y compuestos bioactivos que ayudan a prevenir enfermedades. Por cada 100 gramos cocidos:\n" +
                        "\n" +
                        "Calorías: 35\n" +
                        "Carbohidratos: 7.2 g\n" +
                        "Fibra: 3.3 g\n" +
                        "Proteínas: 2.4 g\n" +
                        "Grasas: 0.4 g"
            }
            "Quinua" -> {
                tvTitulo.text = "Quinua"
                ivImagen.setImageResource(R.drawable.ic_quinua)
                tvDescripcion.text = "La quinua es un pseudocereal reconocido por su alto contenido en proteínas de alta calidad y su perfil de aminoácidos esenciales, ideal para vegetarianos y veganos. Es libre de gluten y rica en fibra, hierro, magnesio y fósforo. Por cada 100 gramos de quinua cocida, aporta:\n" +
                        "\n" +
                        "Calorías: 120\n" +
                        "Carbohidratos: 21.3 g\n" +
                        "Fibra: 2.8 g\n" +
                        "Proteínas: 4.1 g\n" +
                        "Grasas: 1.9 g"
            }
            else -> {
                tvTitulo.text = "Alimento Desconocido"
                ivImagen.setImageResource(R.drawable.ic_food)
                tvDescripcion.text = "No hay información disponible para este alimento."
            }
        }
    }
}
