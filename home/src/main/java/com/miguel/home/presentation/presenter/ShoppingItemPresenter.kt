package com.miguel.home.presentation.presenter

import com.miguel.core.domain.model.ShoppingItemModel
import java.util.*

data class ShoppingItemPresenter(
    val shoppingListId: Long,
    val id: Long,
    var descricao: String = "",
    var quantidade: Double = 1.0,
    var valor: Double = 0.0,
    var estado: Int = 0,
    var ultimaAlteracao: Long = Calendar.getInstance().timeInMillis,
)

fun ShoppingItemPresenter.toModel() = ShoppingItemModel(
    shoppingListId = shoppingListId,
    id = id,
    descricao = descricao,
    quantidade = quantidade,
    valor = valor,
    estado = estado,
    ultimaAlteracao = ultimaAlteracao,
)

fun ShoppingItemModel.toPresenter() = ShoppingItemPresenter(
    shoppingListId = shoppingListId,
    id = id,
    descricao = descricao,
    quantidade = quantidade,
    valor = valor,
    estado = estado,
    ultimaAlteracao = ultimaAlteracao,
)
