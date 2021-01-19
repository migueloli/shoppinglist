package com.miguel.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.miguel.core.data.entities.ShoppingListEntity

@Dao
internal interface ShoppingListDao {

    @Insert
    suspend fun insert(shoppingList: ShoppingListEntity): Long

    @Update
    suspend fun update(shoppingList: ShoppingListEntity)

    @Query("SELECT * FROM shopping_list")
    suspend fun selectAll(): List<ShoppingListEntity>

    @Query("SELECT * FROM shopping_list WHERE id = :id")
    suspend fun selectById(id: Long): ShoppingListEntity

}