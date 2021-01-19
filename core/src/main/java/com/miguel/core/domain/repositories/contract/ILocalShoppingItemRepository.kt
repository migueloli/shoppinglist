package com.miguel.core.domain.repositories.contract

import com.miguel.core.domain.models.ShoppingItemModel

interface ILocalShoppingItemRepository {

    suspend fun inserir(shoppingListModel: ShoppingItemModel): ShoppingItemModel

    suspend fun alterar(shoppingListModel: ShoppingItemModel): ShoppingItemModel

    suspend fun buscarTodos(): List<ShoppingItemModel>

    suspend fun buscarPorId(id: Long): ShoppingItemModel

    suspend fun buscarPorShoppingList(shoppingList: Long): List<ShoppingItemModel>

}