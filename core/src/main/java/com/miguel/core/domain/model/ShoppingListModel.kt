package com.miguel.core.domain.model

import com.miguel.core.data.entity.ShoppingListEntity
import java.util.*

data class ShoppingListModel(
    val id: Long,
    val descricao: String,
    val ativo: Boolean,
    val ultimaAlteracao: Long = Calendar.getInstance().timeInMillis,
)

internal fun ShoppingListModel.toEntity() = ShoppingListEntity(
    id = if(id > 0) id else null,
    descricao = descricao,
    ativo = ativo,
    ultimaAlteracao = ultimaAlteracao
)
