package com.miguel.configuracao.presentation.configuracao

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguel.configuracao.databinding.ConfiguracaoFragmentBinding

class ConfiguracaoFragment : Fragment() {

    private lateinit var _viewModel: ConfiguracaoViewModel
    private val viewModel get() = _viewModel

    private lateinit var _binding: ConfiguracaoFragmentBinding
    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = ConfiguracaoFragmentBinding.inflate(inflater, container, false)
        _viewModel = ViewModelProvider(this).get(ConfiguracaoViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textConfiguracao.text = it
        }
    }

}