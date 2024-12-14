package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterStep1Activity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var nextButton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step1)

        nameEditText = findViewById(R.id.nameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)
        nextButton = findViewById(R.id.nextButtonStep1)

        firebaseAuth = FirebaseAuth.getInstance()

        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val gender = if (genderRadioGroup.checkedRadioButtonId == R.id.maleRadioButton) "Masculino" else "Femenino"
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (name.isNotEmpty() && age.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // Registrar al usuario en Firebase Authentication
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Usuario registrado en Firebase Authentication
                                Toast.makeText(this, "Bien!", Toast.LENGTH_SHORT).show()

                                // Pasar al siguiente paso con los datos adicionales
                                val intent = Intent(this, RegisterStep2Activity::class.java)
                                intent.putExtra("name", name)
                                intent.putExtra("age", age)
                                intent.putExtra("gender", gender)
                                intent.putExtra("email", email) // Opcional, ya registrado
                                startActivity(intent)
                            } else {
                                // Error al registrar en Firebase Authentication
                                Toast.makeText(
                                    this,
                                    "Error al registrar usuario: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
