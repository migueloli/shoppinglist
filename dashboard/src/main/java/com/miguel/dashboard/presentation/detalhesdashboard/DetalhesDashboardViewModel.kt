package com.miguel.dashboard.presentation.detalhesdashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.dashboard.presentation.enums.EstadoClique

class DetalhesDashboardViewModel : ViewModel() {

    private val _detalhesDashboardButton = MutableLiveData<EstadoClique>()
    val detalhesDashboardButton: LiveData<EstadoClique> = _detalhesDashboardButton

    init {
        processarCliqueButtonDetalhesDashboard()
    }

    fun clicarButtonDetalhesDashboard() {
        _detalhesDashboardButton.value = EstadoClique.CLICADO
    }

    fun processarCliqueButtonDetalhesDashboard() {
        _detalhesDashboardButton.value = EstadoClique.AGUARDANDO
    }

}