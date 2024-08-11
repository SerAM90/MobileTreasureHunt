package com.example.mobiletreasurehunt

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mobiletreasurehunt.model.Clues


@Composable
fun ListOfClues(cluesList: List<Clues>, modifier: Modifier = Modifier){
    LazyColumn(modifier = Modifier){
        items(cluesList){

        }
    }
}
