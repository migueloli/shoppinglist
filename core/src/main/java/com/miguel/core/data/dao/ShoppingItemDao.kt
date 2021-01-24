package com.miguel.core.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.miguel.core.data.entity.ShoppingItemEntity

@Dao
internal interface ShoppingItemDao {

    @Insert
    suspend fun insert(shoppingItem: ShoppingItemEntity): Long

    @Update
    suspend fun update(shoppingItem: ShoppingItemEntity)

    @Query("SELECT * FROM shopping_item")
    suspend fun selectAll(): List<ShoppingItemEntity>

    @Query("SELECT * FROM shopping_item WHERE id = :id")
    suspend fun selectById(id: Long): ShoppingItemEntity

    @Query("SELECT * FROM shopping_item WHERE shopping_list = :shoppingList")
    suspend fun selectByShoppingList(shoppingList: Long): List<ShoppingItemEntity>

}