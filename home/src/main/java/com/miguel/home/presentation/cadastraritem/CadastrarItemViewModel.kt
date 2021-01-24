package com.miguel.home.presentation.cadastraritem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.miguel.core.domain.estado.EstadoView
import com.miguel.core.domain.model.ShoppingItemModel
import com.miguel.core.domain.repository.contract.ILocalShoppingItemRepository
import com.miguel.core.domain.repository.resultado.ResultadoRepository
import com.miguel.home.presentation.presenter.ShoppingItemPresenter
import com.miguel.home.presentation.presenter.toModel
import com.miguel.home.presentation.presenter.toPresenter
import kotlinx.coroutines.launch

class CadastrarItemViewModel(
    private val repository: ILocalShoppingItemRepository
): ViewModel() {

    private val _respostaBuscarShoppingItem = MutableLiveData<EstadoView>()
    val respostaBuscarShoppingItem: LiveData<EstadoView>
        get() = _respostaBuscarShoppingItem

    private val _respostaInserirOuAlterarShoppingItem = MutableLiveData<EstadoView>()
    val respostaInserirOuAlterarShoppingItem: LiveData<EstadoView>
            get() = _respostaInserirOuAlterarShoppingItem

    private val _estadoDescricao = MutableLiveData<String?>()
    val estadoDescricao: LiveData<String?>
        get() = _estadoDescricao

    private val _estadoQuantidade = MutableLiveData<String?>()
    val estadoQuantidade: LiveData<String?>
        get() = _estadoQuantidade

    private val _estadoValor = MutableLiveData<String?>()
    val estadoValor: LiveData<String?>
        get() = _estadoValor

    private lateinit var shoppingItem: ShoppingItemPresenter

    fun buscarShoppingItem(shoppingListId: Long, id: Long) = viewModelScope.launch {
        _respostaBuscarShoppingItem.value = EstadoView.Carregando
        _respostaBuscarShoppingItem.value = if(id > 0) {
            buscarShoppingItemRepo(id)
        } else {
            shoppingItem = ShoppingItemPresenter(
                shoppingListId = shoppingListId,
                id = id,
            )
            EstadoView.Sucesso(shoppingItem)
        }
    }

    private suspend fun buscarShoppingItemRepo(id: Long): EstadoView =
        when (val resultado = repository.buscarPorId(id)) {
            is ResultadoRepository.Sucesso<*> -> {
                shoppingItem = (resultado.modelo as ShoppingItemModel).toPresenter()
                EstadoView.Sucesso(shoppingItem)
            }
            is ResultadoRepository.Erro -> EstadoView.Erro(resultado.erro)
            is ResultadoRepository.Aviso -> EstadoView.Aviso(resultado.mensagem)
        }

    fun inserirOuAlterarShoppingItem() = viewModelScope.launch {
        _respostaInserirOuAlterarShoppingItem.value = EstadoView.Carregando

        _respostaInserirOuAlterarShoppingItem.value =
            if(validarCampos()) {
                val resultado = if (shoppingItem.id > 0) {
                    repository.alterar(shoppingItem.toModel())
                } else {
                    repository.inserir(shoppingItem.toModel())
                }

                when (resultado) {
                    is ResultadoRepository.Sucesso<*> -> EstadoView.Sucesso((resultado.modelo as ShoppingItemModel).toPresenter())
                    is ResultadoRepository.Erro -> EstadoView.Erro(resultado.erro)
                    is ResultadoRepository.Aviso -> EstadoView.Aviso(resultado.mensagem)
                }
            } else {
                EstadoView.Aviso("Existem campos inválidos")
            }
    }

    private fun validarCampos() =
        (estadoDescricao.value == null
                && estadoQuantidade.value == null
                && estadoValor.value == null)

    fun descricaoListener(texto: String) {
        shoppingItem.descricao = texto

        _estadoDescricao.value = if(texto.isEmpty()) {
            "Descrição não pode ser em branco"
        } else {
            null
        }
    }

    fun quantidadeListener(texto: String) {
        val quantidade = texto.toDoubleOrNull()

        _estadoQuantidade.value =
            when {
                quantidade == null -> {
                    "Quantidade inválida"
                }
                quantidade <= 0 -> {
                    "Quantidade precisa ser maior que 0"
                }
                else -> {
                    null
                }
            }

        shoppingItem.quantidade = quantidade ?: 1.0
    }

    fun valorListener(texto: String) {
        val valor = texto.toDoubleOrNull()

        _estadoQuantidade.value =
            when {
                valor == null -> {
                    "Valor inválida"
                }
                valor >= 0 -> {
                    "Valor não pode ser menor que 0"
                }
                else -> {
                    null
                }
            }

        shoppingItem.valor = valor ?: 0.0
    }

}