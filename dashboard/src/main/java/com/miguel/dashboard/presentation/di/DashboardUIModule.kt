package com.miguel.dashboard.presentation.di

import com.miguel.dashboard.presentation.dashboard.DashboardViewModel
import com.miguel.dashboard.presentation.detalhesdashboard.DetalhesDashboardViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardUIModule = module {
    viewModel {
        DashboardViewModel()
    }

    viewModel {
        DetalhesDashboardViewModel()
    }
}