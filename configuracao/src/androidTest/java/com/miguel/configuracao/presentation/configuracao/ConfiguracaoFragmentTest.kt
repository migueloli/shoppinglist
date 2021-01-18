package com.miguel.configuracao.presentation.configuracao

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.miguel.configuracao.R
import com.miguel.configuracao.di.configuracaoModule
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
class ConfiguracaoFragmentTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(configuracaoModule)
    }

    @Test
    fun textoViewModel_valorDoTextoDoTextConfiguracao() = runBlockingTest {
        launchFragmentInContainer<ConfiguracaoFragment>(Bundle(), R.style.Theme_MaterialComponents_DayNight)
        Espresso.onView(ViewMatchers.withId(R.id.text_configuracao)).check(
            ViewAssertions.matches(
                ViewMatchers.withText("Este é o ConfiguracaoFragment")
            )
        )
    }

}