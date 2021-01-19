package com.miguel.core.di

import com.miguel.core.data.database.AppDatabase
import com.miguel.core.domain.repositories.LocalShoppingItemRepository
import com.miguel.core.domain.repositories.LocalShoppingListRepository
import com.miguel.core.domain.repositories.contract.ILocalShoppingItemRepository
import com.miguel.core.domain.repositories.contract.ILocalShoppingListRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModules = module {
    single {
        AppDatabase.getInstance(androidContext())
    }

    single {
        get<AppDatabase>().shoppingListDao
    }

    single {
        get<AppDatabase>().shoppingItemDao
    }

    factory<ILocalShoppingListRepository> {
        LocalShoppingListRepository(get())
    }

    factory<ILocalShoppingItemRepository> {
        LocalShoppingItemRepository(get())
    }
}
