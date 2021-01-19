package com.miguel.home.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.core.domain.repositories.contract.ILocalShoppingListRepository
import com.miguel.home.presentation.enums.EstadoClique
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

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