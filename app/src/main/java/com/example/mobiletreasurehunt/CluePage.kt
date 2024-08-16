package com.example.mobiletreasurehunt

import android.annotation.SuppressLint
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
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
import kotlinx.coroutines.withContext


@Composable
fun CluePage(
    navController: NavHostController,
    mobileViewModel: MobileViewModel,
) {
    val uiState by mobileViewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(true){
        if(uiState.isFound){
                Log.i("Location","At the top of Clue Page. Advacning Clue")
                mobileViewModel.advanceClue()
        }
    }
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

@OptIn(UnstableApi::class)
@SuppressLint("MissingPermission", "StateFlowValueCalledInComposition")
@Composable
fun ClueCard(
    clue: Clue,
    hintNumber: Int,
    currentCluePosition: Int,
    navController: NavHostController,
    modifier: Modifier,
    viewModelForPage: MobileViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    val descriptionHolder = stringResource(viewModelForPage.uiState.value.currentClue.clueDescription)
    //dialog state
    val showDialog = remember {mutableStateOf(false)}
    //hint state
    //val currentHintDisplayed = remember {mutableStateOf(hintNumber)}
    Column(modifier = modifier) {
        Text(text = "Clue " + (currentCluePosition + 1)) // Display Clue Number
        Text(text = stringResource(clue.clueDescription)) // Display Clue Text

        Row {
            Button(
                onClick = {

                    Log.i("Location","Checking Clue from Clue PAGE: cluePosition:${viewModelForPage.uiState.value.currentCluePosition}, clueDescription: ${descriptionHolder}")
                    // Start the coroutine to check the clue location
                    scope.launch(Dispatchers.IO) {
                        val resultLocation = locationClient.getCurrentLocation(
                            Priority.PRIORITY_HIGH_ACCURACY,
                            CancellationTokenSource().token
                        ).await()

                        resultLocation?.let { fetchedLocation ->
                            val result = viewModelForPage.checkClue(
                                fetchedLocation.latitude,
                                fetchedLocation.longitude
                            )

                            withContext(Dispatchers.Main) {
                                if (result && viewModelForPage.uiState.value.isFinalClue) {
                                    Log.i("Location","Clue values: isFinalClue ${viewModelForPage.uiState.value.isFinalClue}")
                                    Log.i("Location", "Final clue found. Navigating to completion page.")
                                    navController.navigate(Routes.TreasureHuntCompletedPage)
                                    return@withContext
                                }
                                if(result){
                                    Log.i("Location","Got a match on clue location going to clue solved page")
                                    navController.navigate(Routes.ClueSolvedPage){
                                        launchSingleTop = true
                                    }
                                }
                                else if (!result) {
                                    Log.i("Location", "Location is incorrect.")
                                    // Show some feedback to the user
                                    showDialog.value = true //pop-up
                                }
                            }
                        }
                    }
                }
            ) {
                Text(text = "Found it")
            }

            Button(
                onClick = {
                    viewModelForPage.quit()
                    navController.navigate(Routes.StartPage)
                }
            ) {
                Text(text = "Quit")
            }

            Button(
                onClick = {
                    // Handle hint functionality
                }
            ) {
                Text(text = "Hint")
            }
        }
//        //hint dialogue
//        if (showDialog.value) {
//            Dialog(onDismissRequest = { showDialog.value = false
//            if (currentHintDisplayed.value < clue.clueHint.size -1)
//            currentHintDisplayed.value +=1}) {
//                Box(
//                    modifier = Modifier
//                        .background(Color.White)
//                        .padding(16.dp)
//                        .clip(RoundedCornerShape(8.dp))
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text(text = stringResource(clue.clueHint[currentHintDisplayed.value]))
//
//                        IconButton(onClick = { showDialog.value = false }) {
//                            Icon(
//                                imageVector = Icons.Default.Close,
//                                contentDescription = "Close pop-up"
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
        // Show the dialog if the state is true
        if (showDialog.value) {
            Dialog(onDismissRequest = { showDialog.value = false }) {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.popup))

                        IconButton(onClick = { showDialog.value = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close pop-up"
                            )
                        }
                    }
                }
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