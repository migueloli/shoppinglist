package com.miguel.home.presentation.presenter

import com.miguel.core.domain.model.ShoppingListModel
import java.util.*

data class ShoppingListPresenter(
    val id: Long,
    var descricao: String = "",
    var ativo: Boolean = false,
    var ultimaAlteracao: Long = Calendar.getInstance().timeInMillis,
)

fun ShoppingListPresenter.toModel() = ShoppingListModel(
    id = id,
    descricao = descricao,
    ativo = ativo,
    ultimaAlteracao = ultimaAlteracao,
)

fun ShoppingListModel.toPresenter() = ShoppingListPresenter(
    id = id,
    descricao = descricao,
    ativo = ativo,
    ultimaAlteracao = ultimaAlteracao,
)
