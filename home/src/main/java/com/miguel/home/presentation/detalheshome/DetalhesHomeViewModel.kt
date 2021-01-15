package com.miguel.home.presentation.detalheshome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetalhesHomeViewModel : ViewModel() {

    private val _texto = MutableLiveData<String>()
    val texto: LiveData<String> = _texto

    fun atualizarTexto(texto: String) {
        _texto.value = texto
    }
}