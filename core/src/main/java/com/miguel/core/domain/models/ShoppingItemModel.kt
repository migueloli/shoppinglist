package com.miguel.core.domain.models

import com.miguel.core.data.entities.ShoppingItemEntity

data class ShoppingItemModel(
    val shoppingListId: Long,
    val id: Long,
    val descricao: String,
    val quantidade: Double,
    val valor: Double,
    val estado: Int,
    val ultimaAlteracao: Long,
)

internal fun ShoppingItemModel.toEntity() = ShoppingItemEntity(
    shoppingListId = shoppingListId,
    id = id,
    descricao = descricao,
    quantidade = quantidade,
    valor = valor,
    estado = estado,
    ultimaAlteracao = ultimaAlteracao,
)