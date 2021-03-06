package com.miguel.dashboard.presentation.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.miguel.dashboard.databinding.DashboardFragmentBinding
import com.miguel.dashboard.presentation.estado.EstadoClique
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : Fragment() {

    private lateinit var _binding: DashboardFragmentBinding
    private val binding get() = _binding

    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DashboardFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.dashboardButton.observe(viewLifecycleOwner) { estado ->
            if(estado == EstadoClique.CLICADO) {
                findNavController().navigate(
                    DashboardFragmentDirections.actionDashboardFragmentToDetalhesDashboardFragment()
                )
                viewModel.processarCliqueButtonDashboard()
            }
        }
    }

}