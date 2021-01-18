package com.miguel.configuracao.presentation.configuracao

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguel.configuracao.databinding.ConfiguracaoFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ConfiguracaoFragment : Fragment() {

    private lateinit var _binding: ConfiguracaoFragmentBinding
    private val binding get() = _binding

    private val viewModel: ConfiguracaoViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = ConfiguracaoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textConfiguracao.text = it
        }
    }

}