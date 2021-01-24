package com.miguel.core.domain.repository.contract

import com.miguel.core.domain.model.ShoppingListModel
import com.miguel.core.domain.repository.resultado.ResultadoRepository

interface ILocalShoppingListRepository {

    suspend fun inserir(shoppingListModel: ShoppingListModel): ResultadoRepository

    suspend fun alterar(shoppingListModel: ShoppingListModel): ResultadoRepository

    suspend fun buscarTodos(): ResultadoRepository

    suspend fun buscarPorId(id: Long): ResultadoRepository

}