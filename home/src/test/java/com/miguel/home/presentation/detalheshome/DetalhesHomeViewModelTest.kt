package com.miguel.home.presentation.detalheshome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

class DetalhesHomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var detalhesViewModel: DetalhesHomeViewModel

//    @Before
//    fun setUp() {
//        detalhesViewModel = DetalhesHomeViewModel()
//    }
//
//    @Test
//    fun atualizarTexto_textoPreenchido() {
//        val texto = "Teste"
//        detalhesViewModel.atualizarTexto(texto)
//
//        val value = detalhesViewModel.texto.getOrAwaitValue()
//        MatcherAssert.assertThat(value, Matchers.equalTo(texto))
//    }
//
//    @Test
//    fun atualizarTexto_textoVazio() {
//        detalhesViewModel.atualizarTexto("")
//
//        val value = detalhesViewModel.texto.getOrAwaitValue()
//        MatcherAssert.assertThat(value, Matchers.equalTo(DetalhesHomeViewModel.SEM_INFORMACAO))
//    }

}