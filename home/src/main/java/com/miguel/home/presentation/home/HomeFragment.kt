package com.miguel.home.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miguel.home.databinding.HomeFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var _binding: HomeFragmentBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModel()

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

        binding.buttonHome.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetalhesHomeFragment("Navegado pelo Home"))
        }
    }

}