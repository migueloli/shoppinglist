package com.miguel.home.presentation.detalheshome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetalhesHomeViewModel : ViewModel() {

    companion object {
        const val SEM_INFORMACAO = "Sem informação"
    }

    private val _texto = MutableLiveData<String>()
    val texto: LiveData<String> = _texto

    fun atualizarTexto(texto: String) {
        if(texto.isNotEmpty()) {
            _texto.value = texto
        } else {
            _texto.value = SEM_INFORMACAO
        }
    }

}