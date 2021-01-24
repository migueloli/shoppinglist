package com.miguel.core.domain.repository

import com.miguel.core.data.dao.ShoppingListDao
import com.miguel.core.data.entity.toModel
import com.miguel.core.domain.model.ShoppingListModel
import com.miguel.core.domain.model.toEntity
import com.miguel.core.domain.repository.contract.ILocalShoppingListRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository

internal class LocalShoppingListRepository(
    private val shoppingListDao: ShoppingListDao
): ILocalShoppingListRepository {

    override suspend fun inserir(shoppingListModel: ShoppingListModel) = try {
        val entity = shoppingListModel.toEntity()
        val id = shoppingListDao.insert(entity)
        val ret = entity.copy(id = id).toModel()
        ResultadoRepository.Sucesso(ret)
    } catch(e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun alterar(shoppingListModel: ShoppingListModel) = try {
        val entity = shoppingListModel.toEntity()
        shoppingListDao.update(entity)
        ResultadoRepository.Sucesso(entity.toModel())
    }catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun buscarTodos() = try {
        val list = shoppingListDao.selectAll()
        ResultadoRepository.Sucesso(list.map { it.toModel() })
    } catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

    override suspend fun buscarPorId(id: Long) = try {
        ResultadoRepository.Sucesso(shoppingListDao.selectById(id).toModel())
    } catch (e: Exception) {
        ResultadoRepository.Erro(e)
    }

}