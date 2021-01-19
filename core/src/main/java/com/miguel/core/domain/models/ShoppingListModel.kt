package com.miguel.core.domain.models

import com.miguel.core.data.entities.ShoppingListEntity

data class ShoppingListModel(
    val id: Long,
    val descricao: String,
    val ativo: Boolean,
    val ultimaAlteracao: Long,
)

internal fun ShoppingListModel.toEntity() = ShoppingListEntity(
    id = id,
    descricao = descricao,
    ativo = ativo,
    ultimaAlteracao = ultimaAlteracao
)
