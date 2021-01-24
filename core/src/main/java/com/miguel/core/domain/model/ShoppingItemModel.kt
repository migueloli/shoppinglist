package com.miguel.core.domain.model

import com.miguel.core.data.entity.ShoppingItemEntity
import java.util.*

data class ShoppingItemModel(
    val shoppingListId: Long,
    val id: Long,
    val descricao: String,
    val quantidade: Double,
    val valor: Double,
    val estado: Int,
    val ultimaAlteracao: Long = Calendar.getInstance().timeInMillis,
)

internal fun ShoppingItemModel.toEntity() = ShoppingItemEntity(
    shoppingListId = shoppingListId,
    id = if(id > 0) id else null,
    descricao = descricao,
    quantidade = quantidade,
    valor = valor,
    estado = estado,
    ultimaAlteracao = ultimaAlteracao,
)