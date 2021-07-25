package com.soethan.movietest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.soethan.movietest.databinding.ActivityMainBinding
import com.soethan.movietest.utils.hide
import com.soethan.movietest.utils.show
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.movieDetailFragment || destination.id == R.id.favoriteFragment) {
                binding.navView.hide()
            } else {
                binding.navView.show()
            }
        }

    }

}