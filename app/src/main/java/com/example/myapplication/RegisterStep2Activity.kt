package com.example.myapplication
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterStep2Activity : AppCompatActivity() {

    private lateinit var weightEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step2)

        weightEditText = findViewById(R.id.weightEditText)
        heightEditText = findViewById(R.id.heightEditText)
        nextButton = findViewById(R.id.nextButtonStep2)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")

        nextButton.setOnClickListener {
            val weight = weightEditText.text.toString()
            val height = heightEditText.text.toString()

            if (weight.isNotEmpty() && height.isNotEmpty()) {
                val intent = Intent(this, RegisterStep3Activity::class.java)
                intent.putExtra("name", name)
                intent.putExtra("age", age)
                intent.putExtra("gender", gender)
                intent.putExtra("weight", weight)
                intent.putExtra("height", height)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
