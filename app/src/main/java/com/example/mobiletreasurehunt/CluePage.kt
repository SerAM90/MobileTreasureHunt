package com.example.mobiletreasurehunt

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mobiletreasurehunt.data.Routes
import com.example.mobiletreasurehunt.model.Clue
import com.example.mobiletreasurehunt.ui.theme.MobileViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await



@Composable
fun CluePage(
    navController: NavHostController,
    mobileViewModel: MobileViewModel = viewModel()
) {
    val uiState by mobileViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier) {
        Text(
            text = "Clue " + (uiState.currentCluePosition + 1)
        )

        // ClueCard has to be passed in
        ClueCard(
            clue = uiState.currentClue,
            hintNumber = uiState.currentHint,
            currentCluePosition = uiState.currentCluePosition,
            navController = navController,
            modifier = Modifier,
            viewModelForPage = mobileViewModel
        )
    }
}

@SuppressLint("MissingPermission")
@Composable
fun ClueCard(
    clue: Clue,
    hintNumber: Int,
    currentCluePosition: Int, //clue Position
    navController: NavHostController,
    modifier: Modifier,
    viewModelForPage: MobileViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    Column(modifier = modifier) {
        Text(text = "Clue " + (currentCluePosition + 1)) // +1 because 0 based

        Text(text = stringResource(clue.clueDescription)) //CLUE TEXT

        Row {
            Button(
                onClick = {
                    // Handle button click
                        scope.launch(Dispatchers.IO){
                            val resultLocation = locationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY,
                             CancellationTokenSource().token,).await()
                             resultLocation?.let{ fetchedLocation ->
                                 val result = viewModelForPage.checkClue(fetchedLocation.latitude,fetchedLocation.longitude)
                                if(result && viewModelForPage.uiState.value.isFinalClue ){
                                    //Navigate to final page
                                    Log.i("Location","Result was ${result} and isFinalClue")
                        
                                }
                                if(!result){
                                    //Show a pop up saying they aren't close or something
                                }
                            }
                    
                        }
                        if(viewModelForPage.uiState.value.isFinalClue && viewModelForPage.uiState.value.isFound){
                            navController.navigate(Routes.TreasureHuntCompletedPage)
                        }
                }
            ) {
                Text(text = "Found it")
            }
            Button(
                onClick = {
                    navController.navigate(Routes.StartPage)
                }
            ) {
                Text(text = "Quit")
            }
            Button(
                onClick = {
                    // Handle button click
                }
            ) {
                Text(text = "Hint")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClueCardPreview() {
    ClueCard(
        clue = Clue(
            clueName = R.string.circa1800,
            clueDescription = R.string.clue1_circa1800,
            clueHint = listOf(R.string.hint1_circa1800),
            clueFunFact = R.string.finalcirca1800, //will act the additional info for clue solved, and info about final clue location
            clueLocation = Pair(35.0507, -78.8863), // Coordinates for Circa 1800 pair-double
            imageResourceID = R.drawable.circa1800
        ),
        hintNumber = 0,
        currentCluePosition = 0, // Set a default value for the preview
        navController = rememberNavController(),
        modifier = Modifier,
        viewModelForPage = MobileViewModel()
    )
}