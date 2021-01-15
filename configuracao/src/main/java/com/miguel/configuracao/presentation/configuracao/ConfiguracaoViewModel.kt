package com.miguel.configuracao.presentation.configuracao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConfiguracaoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este é a ConfiguracaoFragment"
    }
    val text: LiveData<String> = _text
}