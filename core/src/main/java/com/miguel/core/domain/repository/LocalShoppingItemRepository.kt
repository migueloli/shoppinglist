package com.miguel.core.domain.repository

import com.miguel.core.data.dao.ShoppingItemDao
import com.miguel.core.data.entity.toModel
import com.miguel.core.domain.model.ShoppingItemModel
import com.miguel.core.domain.model.toEntity
import com.miguel.core.domain.repository.contract.ILocalShoppingItemRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository

internal class LocalShoppingItemRepository(
    private val shoppingItemDao: ShoppingItemDao
): ILocalShoppingItemRepository {

    override suspend fun inserir(shoppingListModel: ShoppingItemModel) = try {
        val entity = shoppingListModel.toEntity()
        val id = shoppingItemDao.insert(entity)
        ResultadoRepository.Sucesso(entity.copy(id = id).toModel())
    } catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun alterar(shoppingListModel: ShoppingItemModel) = try {
        val entity = shoppingListModel.toEntity()
        shoppingItemDao.update(entity)
        ResultadoRepository.Sucesso(entity.toModel())
    } catch(e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun buscarTodos() = try {
        ResultadoRepository.Sucesso(shoppingItemDao.selectAll().map { it.toModel() })
    } catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun buscarPorId(id: Long) = try{
        ResultadoRepository.Sucesso(shoppingItemDao.selectById(id).toModel())
    } catch(e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun buscarPorShoppingList(shoppingList: Long) = try {
        ResultadoRepository.Sucesso(shoppingItemDao.selectByShoppingList(shoppingList).map { it.toModel() })
    } catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

}