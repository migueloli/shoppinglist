package com.miguel.home.presentation.detalheshome

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.miguel.home.R
import com.miguel.home.di.homeModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
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

    @Test
    fun textoViewModel_valorDoTextoDoBundleParaTextDetalhes() = runBlockingTest {
        val texto = "Teste"
        val bundle = DetalhesHomeFragmentArgs.Builder(texto).build().toBundle()
        launchFragmentInContainer<DetalhesHomeFragment>(bundle, R.style.Theme_MaterialComponents_DayNight)
        Espresso.onView(withId(R.id.text_detalhes)).check(matches(withText(texto)))
    }

}