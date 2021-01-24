package com.miguel.core.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.miguel.core.data.dao.ShoppingItemDao
import com.miguel.core.data.dao.ShoppingListDao
import com.miguel.core.data.entity.ShoppingItemEntity
import com.miguel.core.data.entity.ShoppingListEntity

const val VERSION = 1
const val DATABASE_NAME = "shopping_list_database"

@Database(
    entities = [
        ShoppingListEntity::class,
        ShoppingItemEntity::class,
    ],
    version = VERSION,
)
internal abstract class AppDatabase: RoomDatabase() {

    abstract val shoppingListDao: ShoppingListDao
    abstract val shoppingItemDao: ShoppingItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = synchronized(this) {
            var instance = INSTANCE
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                    // TODO: Usado apenas por questão de teste.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

}