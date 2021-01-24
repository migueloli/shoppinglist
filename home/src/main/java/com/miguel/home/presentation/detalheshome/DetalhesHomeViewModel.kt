package com.miguel.home.presentation.detalheshome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.core.domain.estado.EstadoView
import com.miguel.core.domain.model.ShoppingItemModel
import com.miguel.core.domain.repository.contract.ILocalShoppingItemRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository
import com.miguel.home.presentation.presenter.toPresenter
import kotlinx.coroutines.launch

class DetalhesHomeViewModel(
    private val repository: ILocalShoppingItemRepository
): ViewModel() {

    private val _respostaBuscaShoppingItems = MutableLiveData<EstadoView>()
    val respostaBuscaShoppingItems: LiveData<EstadoView> = _respostaBuscaShoppingItems

    fun buscarShoppingItems(shoppingListId: Long) = viewModelScope.launch {
        _respostaBuscaShoppingItems.value = EstadoView.Carregando
        _respostaBuscaShoppingItems.value = when(val result = repository.buscarPorShoppingList(shoppingListId)) {
            is ResultadoRepository.Sucesso<*> -> EstadoView.Sucesso((result.modelo as List<ShoppingItemModel>).map { it.toPresenter() })
            is ResultadoRepository.Erro -> EstadoView.Erro(result.erro)
            is ResultadoRepository.Aviso -> EstadoView.Aviso(result.mensagem)
        }
    }

}