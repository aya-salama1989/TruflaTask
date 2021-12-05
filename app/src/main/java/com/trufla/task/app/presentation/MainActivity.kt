package com.trufla.task.app.presentation

import android.net.NetworkInfo
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.trufla.task.R
import com.trufla.task.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    private val appBarConfiguration by lazy {
        AppBarConfiguration(navController.graph)
    }

    private lateinit var binding: ActivityMainBinding



    private val navController by lazy {
        findNavController(R.id.nav_host_fragment_content_main)
    }

    @Inject
    lateinit var networkInfo: NetworkInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)

        val isConnected: Boolean = networkInfo.isConnectedOrConnecting

        if (!isConnected) {
            Snackbar.make(binding.root, "You are Offline", Snackbar.LENGTH_INDEFINITE)
                .setAction("CLOSE") { }
                .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}