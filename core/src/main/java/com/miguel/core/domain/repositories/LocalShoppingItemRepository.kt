package com.miguel.core.domain.repositories

import com.miguel.core.data.dao.ShoppingItemDao
import com.miguel.core.data.entities.toModel
import com.miguel.core.domain.models.ShoppingItemModel
import com.miguel.core.domain.models.toEntity
import com.miguel.core.domain.repositories.contract.ILocalShoppingItemRepository

internal class LocalShoppingItemRepository(
    private val shoppingItemDao: ShoppingItemDao
): ILocalShoppingItemRepository {

    override suspend fun inserir(shoppingListModel: ShoppingItemModel): ShoppingItemModel {
        val entity = shoppingListModel.toEntity()
        val id = shoppingItemDao.insert(entity)
        return entity.copy(id = id).toModel()
    }

    override suspend fun alterar(shoppingListModel: ShoppingItemModel): ShoppingItemModel {
        val entity = shoppingListModel.toEntity()
        shoppingItemDao.update(entity)
        return entity.toModel()
    }

    override suspend fun buscarTodos(): List<ShoppingItemModel> {
        val list = shoppingItemDao.selectAll()
        return list.map { it.toModel() }
    }

    override suspend fun buscarPorId(id: Long): ShoppingItemModel {
        return shoppingItemDao.selectById(id).toModel()
    }

    override suspend fun buscarPorShoppingList(shoppingList: Long): List<ShoppingItemModel> {
        val list = shoppingItemDao.selectByShoppingList(shoppingList)
        return list.map { it.toModel() }
    }

}