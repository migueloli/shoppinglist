package com.miguel.home.presentation.cadastrarlist

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
import com.miguel.home.databinding.CadastrarListFragmentBinding
import com.miguel.home.presentation.presenter.ShoppingListPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CadastrarListFragment : Fragment() {

    private lateinit var _binding: CadastrarListFragmentBinding
    private val binding get() = _binding

    private val viewModel: CadastrarListViewModel by viewModel()
    private val args: CadastrarListFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CadastrarListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepararEditTexts()
        prepararFloatingActionButton()
        buscaShoppingList()
        insercaoAlteracaoShoppingList()
    }

    private fun prepararEditTexts() {
        prepararCampoDescricao()
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

    private fun prepararFloatingActionButton() = binding.fabConfirmar.setOnClickListener {
        viewModel.inserirOuAlterarShoppingList()
    }

    private fun buscaShoppingList() {
        buscarShoppingList()
        observarBuscaShoppingList()
    }

    private fun buscarShoppingList() =
        viewModel.buscarShoppingList(args.shoppingListId)

    private fun observarBuscaShoppingList() =
        viewModel.respostaBuscarShoppingList.observe(viewLifecycleOwner) { estado ->
            when(estado) {
                EstadoView.Carregando -> carregar(true)
                is EstadoView.Sucesso<*> -> {
                    carregar(false)
                    val modelo = estado.modelo as ShoppingListPresenter
                    binding.run {
                        campoDescricao.setText(modelo.descricao)
                    }
                }
                is EstadoView.Erro -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.erro.message.orEmpty(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is EstadoView.Aviso -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.mensagem,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

    private fun insercaoAlteracaoShoppingList() {
        observarInsercaoAlteracaoShoppingList()
    }

    private fun observarInsercaoAlteracaoShoppingList() =
        viewModel.respostaInserirOuAlterarShoppingList.observe(viewLifecycleOwner) { estado ->
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
                    ).show()
                }
                is EstadoView.Aviso -> {
                    carregar(false)
                    Snackbar.make(
                        requireActivity().findViewById(android.R.id.content),
                        estado.mensagem,
                        Snackbar.LENGTH_SHORT
                    ).show()
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