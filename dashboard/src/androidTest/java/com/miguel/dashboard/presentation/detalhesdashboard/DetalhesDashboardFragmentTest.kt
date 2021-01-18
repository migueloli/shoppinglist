package com.miguel.dashboard.presentation.detalhesdashboard

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.miguel.dashboard.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class DetalhesDashboardFragmentTest {

    @Test
    fun cliqueButtonDetalhes_navegarParaDetalhesHomeFragmentComDeepLink() = runBlockingTest {
        val scenario = launchFragmentInContainer<DetalhesDashboardFragment>(Bundle(), R.style.Theme_MaterialComponents_DayNight)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.button_detalhes)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            Uri.parse("shoppinglist://detalhesHomeFragment/Navegado pela Dashboard")
        )
    }

}