package com.miguel.home.presentation.home

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class HomeFragmentTest {

//    @Test
//    fun cliqueButtonHome_navegarParaDetalhesHomeFragment() = runBlockingTest {
//        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_MaterialComponents_DayNight)
//        val navController = Mockito.mock(NavController::class.java)
//        scenario.onFragment {
//            Navigation.setViewNavController(it.view!!, navController)
//        }
//
//        Espresso.onView(withId(R.id.button_home)).perform(ViewActions.click())
//
//        Mockito.verify(navController).navigate(
//            HomeFragmentDirections.actionHomeFragmentToDetalhesHomeFragment("Navegado pelo Home")
//        )
//    }

}