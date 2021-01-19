package com.miguel.shoppinglist.di

import com.miguel.configuracao.di.configuracaoModule
import com.miguel.core.di.coreModules
import com.miguel.dashboard.di.dashboardModule
import com.miguel.home.di.homeModule

val appModules = coreModules + homeModule + dashboardModule + configuracaoModule