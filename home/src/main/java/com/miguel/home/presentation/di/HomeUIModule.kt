package com.miguel.home.presentation.di

import com.miguel.home.presentation.detalheshome.DetalhesHomeViewModel
import com.miguel.home.presentation.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeUIModule = module {
    viewModel {
        HomeViewModel()
    }

    viewModel {
        DetalhesHomeViewModel()
    }
}