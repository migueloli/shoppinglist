package com.miguel.shoppinglist.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.miguel.shoppinglist.R
import com.miguel.shoppinglist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration.Builder(
            setOf(R.id.homeFragment, R.id.dashboardFragment, R.id.configuracaoFragment),
        ).build()
    }

    private val navController: NavController by lazy { findNavController(R.id.nav_host_fragment) }

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navegacao()
    }

    override fun onSupportNavigateUp() = navController.navigateUp() || super.onSupportNavigateUp()

    private fun navegacao() {
        prepararNavegacao()
        observarNavegacao()
    }

    private fun prepararNavegacao() {
        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun observarNavegacao() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment, R.id.dashboardFragment, R.id.configuracaoFragment -> binding.navView.visibility = View.VISIBLE
                else -> binding.navView.visibility = View.GONE
            }
        }
    }

}