package com.example.mobiletreasurehunt

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun MobileTreasureHuntRules() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            modifier = Modifier.padding(bottom = 16.dp) //title space
        )
        Text("• A good GPS signal is needed to play")
        Text("• You will be timed!")
        Text("• You will need to solve 2 clues in order to find your treasure!")
        Text("• Each clue will have 2 hints to help you solve the clue!")
        Text("• Once you believe you have solved the clue, click the ‘Found it!’ button to check!")
        Text("• Once you have solved both clues and located the treasure, your ‘Found it!’ button will take you to the endgame page!")
        Text("• And most important, HAVE FUN!")

        Button(
            onClick = {
                // Handle button click
            }
        ) {
            Text(text = 'Start')
        }
    }
}