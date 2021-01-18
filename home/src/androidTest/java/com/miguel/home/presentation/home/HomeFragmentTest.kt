package com.miguel.home.presentation.home

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.miguel.home.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

    @Test
    fun cliqueButtonHome_navegarParaDetalhesHomeFragment() = runBlockingTest {
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_MaterialComponents_DayNight)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(withId(R.id.button_home)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            HomeFragmentDirections.actionHomeFragmentToDetalhesHomeFragment("Navegado pelo Home")
        )
    }

}