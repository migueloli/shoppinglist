package com.miguel.dashboard.presentation.detalhesdashboard

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miguel.dashboard.databinding.DetalhesDashboardFragmentBinding

class DetalhesDashboardFragment : Fragment() {

    private lateinit var _viewModel: DetalhesDashboardViewModel
    private val viewModel get() = _viewModel

    private lateinit var _binding: DetalhesDashboardFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = DetalhesDashboardFragmentBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this).get(DetalhesDashboardViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDetalhes.setOnClickListener {
            findNavController().navigate(Uri.parse("shoppinglist://detalhesHomeFragment"))
        }
    }

}