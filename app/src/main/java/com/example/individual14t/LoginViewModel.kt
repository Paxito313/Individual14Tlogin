package com.example.individual14t

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    // LiveData para observar el estado del login
    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean> = _loginResult

    // LiveData para observar los errores
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    // Validación de email y password
    fun validateLogin(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _errorMessage.value = "Ambos campos son obligatorios"
            _loginResult.value = false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _errorMessage.value = "Formato de correo electrónico no valido"
            _loginResult.value = false
        } else if (password.length < 6) {
            _errorMessage.value = "La contraseña debe tener al menos 6 caracteres"
            _loginResult.value = false
        } else {
            _loginResult.value = true
        }
    }
}
