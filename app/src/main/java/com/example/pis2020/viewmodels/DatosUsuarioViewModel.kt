package com.example.pis2020.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pis2020.domain.User
import com.example.pis2020.repositories.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class DatosUsuarioViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UserRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.IO)

    fun createAccount(id: String, email: String, password: String, username: String, age: String,
                      height: String, weight: String) {
        viewModelScope.launch {
            repository.createAccount(id, email, password, username, age.toInt(), height.toDouble(),
                weight.toDouble())
        }
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        /**
         * Creates a new instance of the given `Class`.
         *
         *
         *
         * @param modelClass a `Class` whose instance is requested
         * @param <T>        The type parameter for the ViewModel.
         * @return a newly created ViewModel
        </T> */
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DatosUsuarioViewModel::class.java)) {
                return DatosUsuarioViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}