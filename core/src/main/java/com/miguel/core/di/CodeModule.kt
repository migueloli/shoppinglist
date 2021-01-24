package com.miguel.core.di

import com.miguel.core.data.database.AppDatabase
import com.miguel.core.domain.repository.LocalShoppingItemRepository
import com.miguel.core.domain.repository.LocalShoppingListRepository
import com.miguel.core.domain.repository.contract.ILocalShoppingItemRepository
import com.miguel.core.domain.repository.contract.ILocalShoppingListRepository
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
