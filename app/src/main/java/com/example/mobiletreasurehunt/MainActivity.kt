package com.example.mobiletreasurehunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobiletreasurehunt.data.Routes
import com.example.mobiletreasurehunt.ui.theme.MobileTreasureHuntTheme
import com.example.mobiletreasurehunt.ui.theme.MobileViewModel


class MainActivity : ComponentActivity() {
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(android.Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
            }
            permissions.getOrDefault(android.Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
            } else -> {
            // No location access granted.
        }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mobileViewModel = MobileViewModel()
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION))
        enableEdgeToEdge()
        setContent {
            MobileTreasureHuntTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(innerPadding)) {

                    }
                }
            }
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Routes.StartPage) {
                composable(Routes.StartPage,){
                    MobileTreasureHuntRules(navController)
                }
                composable(Routes.CluePage,){
                    CluePage(navController, mobileViewModel)
                }
                composable(Routes.ClueSolvedPage,){
                    ClueSolvedPage(navController, mobileViewModel)
            }
                composable(Routes.TreasureHuntCompletedPage,){
                    TreasureHuntCompletedPage(navController, mobileViewModel)
                }
        }
    }
}}


