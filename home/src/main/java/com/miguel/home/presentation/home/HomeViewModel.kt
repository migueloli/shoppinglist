package com.miguel.home.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.home.presentation.enums.EstadoClique

class HomeViewModel : ViewModel() {

    private val _homeButton = MutableLiveData<EstadoClique>()
    val homeButton: LiveData<EstadoClique> = _homeButton

    init {
        processarCliqueButtonHome()
    }

    fun clicarButtonHome() {
        _homeButton.value = EstadoClique.CLICADO
    }

    fun processarCliqueButtonHome() {
        _homeButton.value = EstadoClique.AGUARDANDO
    }

}