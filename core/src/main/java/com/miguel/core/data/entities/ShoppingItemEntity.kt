package com.miguel.core.data.entities

import androidx.room.*
import com.miguel.core.domain.models.ShoppingItemModel

@Entity(
    tableName = "shopping_item",
)
internal data class ShoppingItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "descricao") val descricao: String,
    @ColumnInfo(name = "quantidade") val quantidade: Double,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "estado") val estado: Int,
    @ColumnInfo(name = "ultima_alteracao") val ultimaAlteracao: Long,
    @ColumnInfo(name = "shopping_list")
    @ForeignKey(
        entity = ShoppingListEntity::class,
        childColumns = ["shopping_list"],
        parentColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )
    val shoppingListId: Long,
)

internal fun ShoppingItemEntity.toModel() = ShoppingItemModel(
    shoppingListId = shoppingListId,
    id = id,
    descricao = descricao,
    quantidade = quantidade,
    valor = valor,
    estado = estado,
    ultimaAlteracao = ultimaAlteracao,
)
