package com.example.mobiletreasurehunt.ui.theme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.mobiletreasurehunt.data.CluesDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.compose.runtime.setValue

class MobileViewModel: ViewModel() {
    //MobileUIState
    private val clues = CluesDataSource().loadClues()
    private val _uiState = MutableStateFlow(MobileUIState(clues[0]))
    val uiState: StateFlow<MobileUIState> = _uiState.asStateFlow()

    init{
        quit()
    }
fun checkClue(){
    //Get users current position
    //check and use haversin formula from kotlin //https://gist.github.com/jferrao/cb44d09da234698a7feee68ca895f491
    //if in location show next clue, if last clue go to TreasureHuntCompleted Screen
}

fun displayHint(){
        uiState.value.showHint()
}

fun quit(){
    _uiState.value = MobileUIState(clues[0])

}

    //Lets define what functions we need
    //Like check clue + last clue
    //Change clue
    //Hint info
    //Quit button
    //timer

}