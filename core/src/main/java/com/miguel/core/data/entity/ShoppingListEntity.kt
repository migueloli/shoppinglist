package com.miguel.core.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.miguel.core.domain.model.ShoppingListModel

@Entity(
    tableName = "shopping_list"
)
internal data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true) val id: Long?,
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "ativo") val ativo: Boolean,
    @ColumnInfo(name = "ultima_alteracao") val ultimaAlteracao: Long,
)

internal fun ShoppingListEntity.toModel() = ShoppingListModel(
    id = id ?: -1,
    descricao = descricao,
    ativo = ativo,
    ultimaAlteracao = ultimaAlteracao,
)