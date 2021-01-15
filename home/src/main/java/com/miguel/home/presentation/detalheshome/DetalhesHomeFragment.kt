package com.miguel.home.presentation.detalheshome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miguel.home.databinding.DetalhesHomeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetalhesHomeFragment : Fragment() {

    private lateinit var _binding: DetalhesHomeFragmentBinding
    private val binding get() = _binding

    private val viewModel: DetalhesHomeViewModel by viewModel()

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

        texto()
    }

    private fun texto() {
        atualizarTexto()
        observarTexto()
    }

    private fun atualizarTexto() {
        val args = DetalhesHomeFragmentArgs.fromBundle(requireArguments())
        viewModel.atualizarTexto(args.texto)
    }

    private fun observarTexto() {
        viewModel.texto.observe(viewLifecycleOwner) {
            binding.textDetalhes.text = it
        }
    }

}