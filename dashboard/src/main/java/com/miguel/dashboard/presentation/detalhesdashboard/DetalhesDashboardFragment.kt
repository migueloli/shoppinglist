package com.miguel.dashboard.presentation.detalhesdashboard

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miguel.dashboard.databinding.DetalhesDashboardFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetalhesDashboardFragment : Fragment() {

    private lateinit var _binding: DetalhesDashboardFragmentBinding
    private val binding get() = _binding

    private val viewModel: DetalhesDashboardViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DetalhesDashboardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDetalhes.setOnClickListener {
            findNavController().navigate(Uri.parse("shoppinglist://detalhesHomeFragment/Navegado pela Dashboard"))
        }
    }

}