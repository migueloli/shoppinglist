package com.miguel.configuracao.presentation.di

import com.miguel.configuracao.presentation.configuracao.ConfiguracaoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val configuracaoUIModule = module {
    viewModel {
        ConfiguracaoViewModel()
    }
}