package com.miguel.shoppinglist

import android.app.Application
import com.miguel.configuracao.di.configuracaoModule
import com.miguel.dashboard.di.dashboardModule
import com.miguel.home.di.homeModule
import com.miguel.shoppinglist.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }

}