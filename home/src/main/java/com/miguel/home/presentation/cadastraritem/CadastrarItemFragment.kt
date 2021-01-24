package com.miguel.home.presentation.cadastraritem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.miguel.core.domain.estado.EstadoView
import com.miguel.core.extension.converterParaString
import com.miguel.home.databinding.CadastrarItemFragmentBinding
import com.miguel.home.presentation.presenter.ShoppingItemPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarItemFragment: Fragment() {

    private lateinit var _binding: CadastrarItemFragmentBinding
    private val binding get() = _binding

    private val viewModel: CadastrarItemViewModel by viewModel()
    private val args: CadastrarItemFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CadastrarItemFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepararEditTexts()
        prepararFloatingActionButton()
        buscaShoppingItem()
        insercaoAlteracaoShoppingItem()
    }

    private fun prepararEditTexts() {
        prepararCampoDescricao()
        prepararCampoQuantidade()
        prepararCampoValor()
    }

    private fun prepararCampoDescricao() {
        observarCampoDescricao()
        observarErrosCampoDescricao()
    }

    private fun observarCampoDescricao() =
        binding.campoDescricao.doOnTextChanged { text, _, _, _ ->
            viewModel.descricaoListener(text.toString())
        }

    private fun observarErrosCampoDescricao() =
        viewModel.estadoDescricao.observe(viewLifecycleOwner) { erro ->
            binding.layoutDescricao.error = erro
        }

    private fun prepararCampoQuantidade() {
        observarCampoQuantidade()
        observarErrosCampoQuantidade()
    }

    private fun observarCampoQuantidade() =
        binding.campoQuantidade.doOnTextChanged { text, _, _, _ ->
            viewModel.quantidadeListener(text.toString())
        }

    private fun observarErrosCampoQuantidade() =
        viewModel.estadoQuantidade.observe(viewLifecycleOwner) { erro ->
            binding.layoutQuantidade.error = erro
        }

    private fun prepararCampoValor() {
        observarCampoValor()
        observarErrosCampoValor()
    }

    private fun observarCampoValor() =
        binding.campoValor.doOnTextChanged { text, _, _, _ ->
            viewModel.valorListener(text.toString())
        }

    private fun observarErrosCampoValor() =
        viewModel.estadoValor.observe(viewLifecycleOwner) { erro ->
            binding.layoutValor.error = erro
        }

    private fun prepararFloatingActionButton() = binding.fabConfirmar.setOnClickListener {
        viewModel.inserirOuAlterarShoppingItem()
    }

    private fun buscaShoppingItem() {
        buscarShoppingItem()
        observarBuscaShoppingItem()
    }

    private fun buscarShoppingItem() =
        viewModel.buscarShoppingItem(args.shoppingListId, args.shoppingItemId)

    private fun observarBuscaShoppingItem() =
        viewModel.respostaBuscarShoppingItem.observe(viewLifecycleOwner) { estado ->
            when(estado) {
                EstadoView.Carregando -> carregar(true)
                is EstadoView.Sucesso<*> -> {
                    carregar(false)
                    val modelo = estado.modelo as ShoppingItemPresenter
                    binding.run {
                        campoDescricao.append(modelo.descricao)
                        campoQuantidade.append(modelo.quantidade.converterParaString())
                        campoValor.append(modelo.valor.converterParaString())
                        invalidateAll()
                    }
                }
                is EstadoView.Erro -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.erro.message.orEmpty(),
                        Snackbar.LENGTH_SHORT
                    )
                }
                is EstadoView.Aviso -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.mensagem,
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }

    private fun insercaoAlteracaoShoppingItem() {
        observarInsercaoAlteracaoShoppingItem()
    }

    private fun observarInsercaoAlteracaoShoppingItem() =
        viewModel.respostaInserirOuAlterarShoppingItem.observe(viewLifecycleOwner) { estado ->
            when(estado) {
                EstadoView.Carregando -> carregar(true)
                is EstadoView.Sucesso<*> -> {
                    carregar(false)
                    navController.popBackStack()
                }
                is EstadoView.Erro -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.erro.message.orEmpty(),
                        Snackbar.LENGTH_SHORT
                    )
                }
                is EstadoView.Aviso -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.mensagem,
                        Snackbar.LENGTH_SHORT
                    )
                }
            }
        }

    private fun carregar(carregar: Boolean) = binding.estadoFlipper.run {
        displayedChild = indexOfChild(
            if(carregar) binding.carregando.carregandoLayout
            else binding.cadastroScroll
        )
    }

}