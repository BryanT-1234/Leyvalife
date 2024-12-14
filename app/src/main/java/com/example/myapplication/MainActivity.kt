package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        auth = FirebaseAuth.getInstance()

        val menuButton: ImageButton = findViewById(R.id.menuButton)

        // Abrir el Navigation Drawer
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        // Configurar navegación entre fragmentos
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_mi_salud -> loadFragment(MiSaludFragment())
                R.id.nav_comidas -> loadFragment(ComidasFragment())
                R.id.nav_ejercicios -> loadFragment(EjerciciosFragment())
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        // Cargar el fragmento por defecto
        if (savedInstanceState == null) {
            loadFragment(MiSaludFragment())
        }

        // Configurar nombre y correo en el header
        val headerView = navigationView.getHeaderView(0)
        val userNameTextView: TextView = headerView.findViewById(R.id.profileName)
        val userEmailTextView: TextView = headerView.findViewById(R.id.profileEmail)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Configurar datos del usuario en el header
            userNameTextView.text = currentUser.displayName ?: "Usuario"
            userEmailTextView.text = currentUser.email ?: "Correo no disponible"
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
