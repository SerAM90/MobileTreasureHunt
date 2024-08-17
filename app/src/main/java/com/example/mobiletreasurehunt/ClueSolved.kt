package com.example.mobiletreasurehunt

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mobiletreasurehunt.data.Routes
import com.example.mobiletreasurehunt.model.Clue
import com.example.mobiletreasurehunt.ui.theme.MobileViewModel

//*********
//Andrew Serrano
//Oregon State University
//CS 492- Mobile Software Development **Summer 2024
//Assignment 5: Fayetteville Treasure Hunt
//******************
@Composable
fun ClueSolvedPage(
    navController: NavHostController,
    mobileViewModel: MobileViewModel,
) {
    val uiState by mobileViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier) {
        Text(
            text = "Clue " + (uiState.currentClue)
        )

        // ClueCard has to be passed in
        ClueSolvedCard(clue = uiState.currentClue ,
            name = "name",
            currentCluePosition = uiState.currentCluePosition ,
            isFinalClue = uiState.isFinalClue,
            navHost = navController,
            mobileViewModel = mobileViewModel,
            modifier = Modifier )
    }
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ClueSolvedCard(clue: Clue, name: String, currentCluePosition: Int, isFinalClue: Boolean,navHost: NavHostController, mobileViewModel: MobileViewModel, modifier: Modifier){
    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        //image
         val descriptionHolder = stringResource(mobileViewModel.uiState.value.currentClue.clueDescription)
        Log.i("Location","Checking Clue from CLUE SOLVED: cluePosition:${mobileViewModel.uiState.value.currentCluePosition}, clueDescription: ${descriptionHolder}")

        Image(painter = painterResource(id = clue.imageResourceID), contentDescription = stringResource(clue.clueName
        ))
        Text(
            text = "Current Time: "
        )
        Text(
            text = "Clue Solved! " + stringResource(clue.clueName))
        Text(
            text = "Fun Fact: " + stringResource(clue.clueFunFact)
        )
        Button(
            onClick = {
                // Handle button click
                if(isFinalClue){
                    navHost.navigate(Routes.TreasureHuntCompletedPage)
                }
                else{
                      //scope.launch(Dispatchers.IO) {
                       // withContext(Dispatchers.Main){
                    navHost.navigate(Routes.CluePage){
                                        launchSingleTop = true
                                    }
                       // }
                        //mobileViewModel.advanceClue()
                   // }
                }
            }
        ) {
            Text(text = "Continue")
        }
    }
}
//@Preview(showBackground = true)//nav and final clue to fix it later
//@Composable
//fun ClueSolvedCardPreview() {
//    ClueSolvedCard(
//        clue = Clue(
//            clueName = R.string.circa1800,
//            clueDescription = R.string.clue1_circa1800,
//            clueHint = listOf(R.string.hint1_circa1800),
//            clueFunFact = R.string.finalcirca1800,
//            clueLocation = Pair(35.0507, -78.8863),
//            imageResourceID = R.drawable.circa1800
//        ),
//        name = "Sample Name", // need to add a name
//        currentCluePosition = 1, // don't need so will need to adjust
//        modifier = Modifier
//    )
//}