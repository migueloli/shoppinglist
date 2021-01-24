package com.miguel.home.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.core.domain.estado.EstadoView
import com.miguel.core.domain.model.ShoppingListModel
import com.miguel.core.domain.repository.contract.ILocalShoppingListRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository
import com.miguel.home.presentation.presenter.toPresenter
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ILocalShoppingListRepository
) : ViewModel() {

    private val _respostaBuscaShoppingList = MutableLiveData<EstadoView>()
    val respostaBuscaShoppingList: LiveData<EstadoView> = _respostaBuscaShoppingList

    fun buscarShoppingList() =
        viewModelScope.launch {
            _respostaBuscaShoppingList.value = EstadoView.Carregando
            _respostaBuscaShoppingList.value = when (val result = repository.buscarTodos()) {
                is ResultadoRepository.Sucesso<*> -> EstadoView.Sucesso((result.modelo as List<ShoppingListModel>).map { it.toPresenter() })
                is ResultadoRepository.Erro -> EstadoView.Erro(result.erro)
                is ResultadoRepository.Aviso -> EstadoView.Aviso(result.mensagem)
            }
        }

}