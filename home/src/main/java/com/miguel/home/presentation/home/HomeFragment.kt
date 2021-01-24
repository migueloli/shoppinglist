package com.miguel.home.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.miguel.core.domain.estado.EstadoView
import com.miguel.home.databinding.HomeFragmentBinding
import com.miguel.home.presentation.home.adapter.ShoppingListAdapter
import com.miguel.home.presentation.home.adapter.listener.ShoppingListClickListener
import com.miguel.home.presentation.presenter.ShoppingListPresenter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModel()
    private val navController: NavController by lazy { findNavController() }

    private lateinit var adaptador: ShoppingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepararFloatingActionButton()
        prepararRecyclerView()
        buscaShoppingList()
    }

    private fun prepararFloatingActionButton() = binding.fabAdicionar.setOnClickListener {
        navController.navigate(HomeFragmentDirections.actionHomeFragmentToCadastrarListFragment(-1))
    }

    private fun prepararRecyclerView() = binding.homeList.run {
        setHasFixedSize(true)
        adaptador = ShoppingListAdapter(ShoppingListClickListener(
            {
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetalhesHomeFragment(
                        it
                    )
                )
            },
            {
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToCadastrarListFragment(
                        it
                    )
                )
                true
            }
        ))
        adapter = adaptador
    }

    private fun buscaShoppingList() {
        buscarShoppingList()
        observarBuscaShoppingList()
    }

    private fun buscarShoppingList() = viewModel.buscarShoppingList()

    private fun observarBuscaShoppingList() =
        viewModel.respostaBuscaShoppingList.observe(viewLifecycleOwner) { estado ->
            when(estado) {
                EstadoView.Carregando -> carregar(true)
                is EstadoView.Sucesso<*> -> {
                    carregar(false)
                    val list = estado.modelo as List<ShoppingListPresenter>
                    adaptador.submitList(list)
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
            else binding.homeList
        )
    }

}