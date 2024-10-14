package com.example.individual14t

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.individual14t.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Usamos ViewBinding para simplificar el acceso a las vistas
    private lateinit var binding: ActivityMainBinding

    // Obtenemos una instancia del ViewModel
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de login
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()

            // Llamamos al ViewModel para validar los datos
            viewModel.validateLogin(email, password)
        }

        // Observamos los resultados del ViewModel
        viewModel.loginResult.observe(this, Observer { success ->
            if (success) {
                // Login exitoso - mostrar el Toast
                Toast.makeText(this, "¡Inicio de sesión exitoso!", Toast.LENGTH_SHORT).show()

                // Ocultar el texto de error si es visible
                binding.errorText.visibility = android.view.View.GONE

                // Aquí podrías navegar a otra actividad o realizar otras acciones
            } else {
                // Mostrar mensaje de error
                binding.errorText.visibility = android.view.View.VISIBLE
            }
        })

        // Observamos los mensajes de error
        viewModel.errorMessage.observe(this, Observer { message ->
            binding.errorText.text = message
        })
    }
}
