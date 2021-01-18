package com.miguel.configuracao.presentation.configuracao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfiguracaoViewModel : ViewModel() {
    private val _texto = MutableLiveData<String>().apply {
        value = "Este é o ConfiguracaoFragment"
    }
    val texto: LiveData<String> = _texto
}