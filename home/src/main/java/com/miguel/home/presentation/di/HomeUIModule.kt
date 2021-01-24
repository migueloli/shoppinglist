package com.miguel.home.presentation.di

import com.miguel.home.presentation.cadastraritem.CadastrarItemViewModel
import com.miguel.home.presentation.cadastrarlist.CadastrarListViewModel
import com.miguel.home.presentation.detalheshome.DetalhesHomeViewModel
import com.miguel.home.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeUIModule = module {

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetalhesHomeViewModel(get())
    }

    viewModel {
        CadastrarItemViewModel(get())
    }

    viewModel {
        CadastrarListViewModel(get())
    }

}