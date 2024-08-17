package com.example.mobiletreasurehunt

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mobiletreasurehunt.data.Routes
//*********
//Andrew Serrano
//Oregon State University
//CS 492- Mobile Software Development **Summer 2024
//Assignment 5: Fayetteville Treasure Hunt
//******************
@Composable
fun MobileTreasureHuntRules(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize(),  //fill
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.title),
                modifier = Modifier.padding(bottom = 16.dp) //needed padding for title
            )
            Text("• A good GPS signal is needed to play")
            Text("• You will be timed!")
            Text("• You will need to solve 2 clues in order to find your treasure!")
            Text("• Each clue will have 2 hints to help you solve the clue!")
            Text("• Once you believe you have solved the clue, click the ‘Found it!’ button to check!")
            Text("• Once you have solved both clues and located the treasure, your ‘Found it!’ button will take you to the endgame page!")
            Text("• And most important, HAVE FUN!")
            Row {
                Button(
                    onClick = {
                        navController.navigate(Routes.CluePage) {
                            launchSingleTop = true
                        }
                    }
                ) {
                    Text(text = "Start")
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun StartPagePreview(){
//    MobileTreasureHuntRules()
//}