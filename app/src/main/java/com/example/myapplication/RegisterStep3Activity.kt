package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterStep3Activity : AppCompatActivity() {

    private lateinit var imcTextView: TextView
    private lateinit var imcInterpretationTextView: TextView
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step3)

        imcTextView = findViewById(R.id.imcTextView)
        imcInterpretationTextView = findViewById(R.id.imcInterpretationTextView)
        registerButton = findViewById(R.id.registerButtonStep3)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")
        val weight = intent.getStringExtra("weight")?.toDoubleOrNull() ?: 0.0
        val height = intent.getStringExtra("height")?.toDoubleOrNull() ?: 1.0

        val imc = weight / (height * height)
        imcTextView.text = "SU IMC ES: ${String.format("%.2f", imc)}"
        imcInterpretationTextView.text = interpretImc(imc)

        registerButton.setOnClickListener {
            val firebaseAuth = FirebaseAuth.getInstance()
            val firebaseDatabase = FirebaseDatabase.getInstance()
            val usersRef = firebaseDatabase.getReference("users")

            val userId = firebaseAuth.currentUser?.uid ?: ""
            val user = mapOf(
                "name" to name,
                "age" to age,
                "gender" to gender,
                "weight" to weight.toString(),
                "height" to height.toString(),
                "imc" to imc.toString(),
                "imcInterpretation" to interpretImc(imc)
            )

            usersRef.child(userId).setValue(user).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

                    // Redirigir al LoginActivity
                    val intent = Intent(this, LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish() // Finalizar la actividad actual para evitar que el usuario regrese
                } else {
                    Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun interpretImc(imc: Double): String {
        return when {
            imc < 18.5 -> "Por debajo del peso,\nhay que subir ya!"
            imc in 18.5..24.9 -> "Peso ideal,\npodemos ganar músculos!"
            imc in 25.0..29.9 -> "Presenta Sobrepeso,\nhora de bajar esa barriga!"
            else -> "Tiene Obesidad\nhay que bajar de peso ya!"
        }
    }
}
