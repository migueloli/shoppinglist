package com.miguel.dashboard.presentation.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguel.dashboard.presentation.enums.EstadoClique

class DashboardViewModel : ViewModel() {

    private val _dashboardButton = MutableLiveData<EstadoClique>()
    val dashboardButton: LiveData<EstadoClique> = _dashboardButton

    init {
        processarCliqueButtonDashboard()
    }

    fun clicarButtonDashboard() {
        _dashboardButton.value = EstadoClique.CLICADO
    }

    fun processarCliqueButtonDashboard() {
        _dashboardButton.value = EstadoClique.AGUARDANDO
    }

}