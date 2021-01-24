package com.miguel.home.presentation.detalheshome

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.miguel.home.di.homeModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class DetalhesHomeFragmentTest: KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(homeModule)
    }

//    @Test
//    fun textoViewModel_valorDoTextoDoBundleParaTextDetalhes() = runBlockingTest {
//        val texto = "Teste"
//        val bundle = DetalhesHomeFragmentArgs.Builder(texto).build().toBundle()
//        launchFragmentInContainer<DetalhesHomeFragment>(bundle, R.style.Theme_MaterialComponents_DayNight)
//        Espresso.onView(withId(R.id.text_detalhes)).check(matches(withText(texto)))
//    }

}