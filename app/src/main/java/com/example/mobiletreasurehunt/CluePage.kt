package com.example.mobiletreasurehunt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
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

){
    val uiState by mobileViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier
    ) {
        Text(
            text = "Clue" + (uiState.currentCluePosition + 1))
    }

}
@Composable
fun ClueCard(clue: Clue, hintNumber: Int, modifier: Modifier ){
//clue text
//hint button with hint
//quit button
//found it button
    Column(){
        Text(text = stringResource(clue.clueDescription))
        //Text(text = stringResource(R.string.clue1_circa1800))
    }
}

@Preview(showBackground = true)
@Composable
fun ClueCardPreview(){
    ClueCard(clue = Clue(
                clueName = R.string.circa1800,
                clueDescription = R.string.clue1_circa1800,
                clueHint = listOf(R.string.hint1_circa1800), //R.string.hint1_circa1800,
                clueLocation = Pair(35.0507, -78.8863), //coordinates for Circa 1800
                imageResourceID = R.drawable.circa1800
            ),hintNumber =0, modifier = Modifier)
}

//@Composable
//fun ListOfClues(cluesList: List<Clue>, modifier: Modifier = Modifier){
//    LazyColumn(modifier = Modifier){
//        items(cluesList){clues ->
//
//        }
//    }
//}
