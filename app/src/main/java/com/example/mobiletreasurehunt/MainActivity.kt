package com.example.mobiletreasurehunt

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.mobiletreasurehunt.ui.theme.MobileTreasureHuntTheme
import android.Manifest
import android.app.AlertDialog
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat

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
        locationPermissionRequest.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION))
        enableEdgeToEdge()
        setContent {
            MobileTreasureHuntTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        RequestPermissionButton(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RequestPermissionButton( modifier: Modifier) {
    Button(onClick = {

    }){
        Text(text = "Check Permission")
    }
}
//class MainActivity : ComponentActivity() {
//
//    //private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
//    private val requestPermissionLauncher =  registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                Log.i("Permission: ", "Granted")
//            } else {
//                Log.i("Permission: ", "Denied")
//                //requestPermission()
//            }
//        }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//        // Call the requestPermission method here
//        requestPermission()
//
//        setContent {
//            MobileTreasureHuntTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    // Display the actual app
//                }
//            }
//       }
//          requestPermissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestPermission()
//        ) { isGranted: Boolean ->
//            if (isGranted) {
//                Log.i("Permission: ", "Granted")
//            } else {
//                Log.i("Permission: ", "Denied")
//                //requestPermission()
//            }
//        }
//        requestPermission()
//    }
//
//    private fun requestPermission() {
//        when {
//            ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_BACKGROUND_LOCATION
//            ) == PackageManager.PERMISSION_GRANTED -> {
//                // Permission is granted
//                Log.i("Permission: ", "Granted")
//            }
//
//            ActivityCompat.shouldShowRequestPermissionRationale(
//                this,
//                Manifest.permission.ACCESS_BACKGROUND_LOCATION
//            ) -> {
//                Log.i("Permission: ","Attempting to show dialog to access location")
//                // Create and show an AlertDialog in Kotlin
//                AlertDialog.Builder(this)
//                    .setMessage("This application requires access to your location in order to function properly.")
//                    .setPositiveButton("OK") { _, _ ->
//                        // Request the permission after showing the rationale
//                        Log.i("Permission: ","Attempting to show requestPermissionLauncher")
//                        requestPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//                    }
//                    .setNegativeButton("Cancel") { dialog, _ ->
//                        //cancel
//                        dialog.dismiss()
//                    }
//                    .create()
//                    .show()
//
//                Log.i("Permission: ", "Rationale Needed")
//            }
//
//            else -> {
//                requestPermissionLauncher.launch(
//                    Manifest.permission.ACCESS_BACKGROUND_LOCATION
//                )
//            }
//        }
//    }
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MobileTreasureHuntTheme {
//        Greeting("Android")
//    }
//}