package com.miguel.dashboard.presentation.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miguel.dashboard.databinding.DashboardFragmentBinding

class DashboardFragment : Fragment() {

    private lateinit var _viewModel: DashboardViewModel
    private val viewModel get() = _viewModel

    private lateinit var _binding: DashboardFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardFragmentBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDashboard.setOnClickListener {
            findNavController().navigate(DashboardFragmentDirections.actionDashboardFragmentToDetalhesDashboardFragment())
        }
    }

}