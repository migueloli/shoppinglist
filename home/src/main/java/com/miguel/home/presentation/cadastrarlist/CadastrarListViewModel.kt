package com.miguel.home.presentation.cadastrarlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.core.domain.estado.EstadoView
import com.miguel.core.domain.model.ShoppingListModel
import com.miguel.core.domain.repository.contract.ILocalShoppingListRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository
import com.miguel.home.presentation.presenter.ShoppingListPresenter
import com.miguel.home.presentation.presenter.toModel
import com.miguel.home.presentation.presenter.toPresenter
import kotlinx.coroutines.launch

class CadastrarListViewModel(
    private val repository: ILocalShoppingListRepository
): ViewModel() {
    private val _respostaBuscarShoppingList = MutableLiveData<EstadoView>()
    val respostaBuscarShoppingList: LiveData<EstadoView>
        get() = _respostaBuscarShoppingList

    private val _respostaInserirOuAlterarShoppingList = MutableLiveData<EstadoView>()
    val respostaInserirOuAlterarShoppingList: LiveData<EstadoView>
        get() = _respostaInserirOuAlterarShoppingList

    private val _estadoDescricao = MutableLiveData<String?>()
    val estadoDescricao: LiveData<String?>
        get() = _estadoDescricao

    private lateinit var shoppingList: ShoppingListPresenter

    fun buscarShoppingList(id: Long) = viewModelScope.launch {
        _respostaBuscarShoppingList.value = EstadoView.Carregando
        _respostaBuscarShoppingList.value = if(id > 0) {
            buscarShoppingListRepo(id)
        } else {
            shoppingList = ShoppingListPresenter(
                id = id,
            )
            EstadoView.Sucesso(shoppingList)
        }
    }

    private suspend fun buscarShoppingListRepo(id: Long): EstadoView =
        when (val resultado = repository.buscarPorId(id)) {
            is ResultadoRepository.Sucesso<*> -> {
                shoppingList = (resultado.modelo as ShoppingListModel).toPresenter()
                EstadoView.Sucesso(shoppingList)
            }
            is ResultadoRepository.Erro -> EstadoView.Erro(resultado.erro)
            is ResultadoRepository.Aviso -> EstadoView.Aviso(resultado.mensagem)
        }

    fun inserirOuAlterarShoppingList() = viewModelScope.launch {
        _respostaInserirOuAlterarShoppingList.value = EstadoView.Carregando

        _respostaInserirOuAlterarShoppingList.value =
            if(validarCampos()) {
                val resultado = if (shoppingList.id > 0) {
                    repository.alterar(shoppingList.toModel())
                } else {
                    repository.inserir(shoppingList.toModel())
                }

                when (resultado) {
                    is ResultadoRepository.Sucesso<*> -> {
                        val modelo = (resultado.modelo as ShoppingListModel).toPresenter()
                        val list = (repository.buscarTodos() as ResultadoRepository.Sucesso<*>).modelo as List<ShoppingListModel>
                        EstadoView.Sucesso(modelo)
                    }
                    is ResultadoRepository.Erro -> EstadoView.Erro(resultado.erro)
                    is ResultadoRepository.Aviso -> EstadoView.Aviso(resultado.mensagem)
                }
            } else {
                EstadoView.Aviso("Existem campos inválidos")
            }
    }

    private fun validarCampos() = (estadoDescricao.value == null)

    fun descricaoListener(texto: String) {
        shoppingList.descricao = texto

        _estadoDescricao.value = if(texto.isEmpty()) {
            "Descrição não pode ser em branco"
        } else {
            null
        }
    }

}