package com.example.mobiletreasurehunt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobiletreasurehunt.model.Clue
import com.example.mobiletreasurehunt.ui.theme.MobileUIState
import com.example.mobiletreasurehunt.ui.theme.MobileViewModel



@Composable
fun CluePage(
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
            modifier = Modifier
        )
    }
}

@Composable
fun ClueCard(
    clue: Clue,
    hintNumber: Int,
    currentCluePosition: Int, //clue Position
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Clue " + (currentCluePosition + 1)) // +1 because 0 based

        Text(text = stringResource(clue.clueDescription)) //CLUE TEXT

        Row {
            Button(
                onClick = {
                    // Handle button click
                }
            ) {
                Text(text = "Found it")
            }
            Button(
                onClick = {
                    // Handle button click
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
        modifier = Modifier
    )
}