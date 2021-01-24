package com.miguel.home.presentation.detalheshome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.miguel.core.domain.estado.EstadoView
import com.miguel.home.databinding.DetalhesHomeFragmentBinding
import com.miguel.home.presentation.detalheshome.adapter.ShoppingItemAdapter
import com.miguel.home.presentation.detalheshome.adapter.listener.ShoppingItemClickListener
import com.miguel.home.presentation.presenter.ShoppingItemPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetalhesHomeFragment : Fragment() {

    private lateinit var _binding: DetalhesHomeFragmentBinding
    private val binding get() = _binding

    private val viewModel: DetalhesHomeViewModel by viewModel()
    private val args: DetalhesHomeFragmentArgs by navArgs()
    private val navController: NavController by lazy { findNavController() }

    private lateinit var adaptador: ShoppingItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DetalhesHomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepararFloatingActionButton()
        prepararRecyclerView()
        buscaShoppingItems()
    }

    private fun prepararFloatingActionButton() = binding.fabAdicionar.setOnClickListener {
        navController.navigate(DetalhesHomeFragmentDirections.actionDetalhesHomeFragmentToCadastrarItemFragment(args.shoppingListId, -1))
    }

    private fun prepararRecyclerView() = binding.detalhesList.run {
        setHasFixedSize(true)
        adaptador = ShoppingItemAdapter(ShoppingItemClickListener {
            navController.navigate(DetalhesHomeFragmentDirections.actionDetalhesHomeFragmentToCadastrarItemFragment(args.shoppingListId, it))
        })
        adapter = adaptador
    }

    private fun buscaShoppingItems() {
        buscarShoppintItems()
        observarBuscaShoppingItems()
    }

    private fun buscarShoppintItems() = viewModel.buscarShoppingItems(args.shoppingListId)

    private fun observarBuscaShoppingItems() =
        viewModel.respostaBuscaShoppingItems.observe(viewLifecycleOwner) { estado ->
            when(estado) {
                EstadoView.Carregando -> carregar(true)
                is EstadoView.Sucesso<*> -> {
                    carregar(false)
                    adaptador.submitList(estado.modelo as List<ShoppingItemPresenter>)
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
            else binding.detalhesList
        )
    }

}