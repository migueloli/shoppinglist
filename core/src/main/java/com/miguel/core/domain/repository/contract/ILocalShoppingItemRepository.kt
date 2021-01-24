package com.miguel.core.domain.repository.contract

import com.miguel.core.domain.model.ShoppingItemModel
import com.miguel.core.domain.repository.resultado.ResultadoRepository

interface ILocalShoppingItemRepository {

    suspend fun inserir(shoppingListModel: ShoppingItemModel): ResultadoRepository

    suspend fun alterar(shoppingListModel: ShoppingItemModel): ResultadoRepository

    suspend fun buscarTodos(): ResultadoRepository

    suspend fun buscarPorId(id: Long): ResultadoRepository

    suspend fun buscarPorShoppingList(shoppingList: Long): ResultadoRepository

}