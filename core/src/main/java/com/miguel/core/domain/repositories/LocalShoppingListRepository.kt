package com.miguel.core.domain.repositories

import com.miguel.core.data.dao.ShoppingListDao
import com.miguel.core.data.entities.toModel
import com.miguel.core.domain.models.ShoppingListModel
import com.miguel.core.domain.models.toEntity
import com.miguel.core.domain.repositories.contract.ILocalShoppingListRepository

internal class LocalShoppingListRepository(
    private val shoppingListDao: ShoppingListDao
): ILocalShoppingListRepository {

    override suspend fun inserir(shoppingListModel: ShoppingListModel): ShoppingListModel {
        val entity = shoppingListModel.toEntity()
        val id = shoppingListDao.insert(entity)
        return entity.copy(id = id).toModel()
    }

    override suspend fun alterar(shoppingListModel: ShoppingListModel): ShoppingListModel {
        val entity = shoppingListModel.toEntity()
        shoppingListDao.update(entity)
        return entity.toModel()
    }

    override suspend fun buscarTodos(): List<ShoppingListModel> =
        shoppingListDao.selectAll().map { it.toModel() }

    override suspend fun buscarPorId(id: Long): ShoppingListModel =
        shoppingListDao.selectById(id).toModel()

}