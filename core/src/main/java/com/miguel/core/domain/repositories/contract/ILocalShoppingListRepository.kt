package com.miguel.core.domain.repositories.contract

import com.miguel.core.domain.models.ShoppingListModel

interface ILocalShoppingListRepository {

    suspend fun inserir(shoppingListModel: ShoppingListModel): ShoppingListModel

    suspend fun alterar(shoppingListModel: ShoppingListModel): ShoppingListModel

    suspend fun buscarTodos(): List<ShoppingListModel>

    suspend fun buscarPorId(id: Long): ShoppingListModel

}