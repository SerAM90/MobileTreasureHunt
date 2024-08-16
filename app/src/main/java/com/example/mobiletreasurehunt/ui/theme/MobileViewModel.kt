package com.example.mobiletreasurehunt.ui.theme
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.mobiletreasurehunt.data.CluesDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


class MobileViewModel: ViewModel() {
    //MobileUIState
    private val clues = CluesDataSource().loadClues()
    private val _uiState = MutableStateFlow(MobileUIState(clues[0]))
    val uiState: StateFlow<MobileUIState> = _uiState.asStateFlow()


    //init{
      //  quit()
   // }
fun checkClue(lat: Double, long: Double) : Boolean{
    //Get users current position
    //check and use haversine formula from kotlin //https://gist.github.com/jferrao/cb44d09da234698a7feee68ca895f491
    //if in location show next clue, if last clue go to TreasureHuntCompleted Screen
    val haversineResult = haversineFormulaDistance(lat,long,uiState.value.currentClue.clueLocation.first,uiState.value.currentClue.clueLocation.second)
    Log.i("location","haversine Result is ${haversineResult}")
    if(haversineResult <= 25){
        //Advance Clue
//        val currentClue = uiState.value.currentCluePosition
//         _uiState.value.isFound = true
//        if(currentClue == clues.size){
//            //We solved all the clues
//            _uiState.value.isFinalClue = true
//            _uiState.emit(_uiState.value)
//        }
//        else{
//            _uiState.value.currentCluePosition = currentClue+1
//            _uiState.value.currentClue = clues[currentClue +1]
//               Log.i("location","Advancing Clue")
//              // _uiState.emit(_uiState.value)
//            val holderState = MobileUIState(clues[currentClue+1],_uiState.value.currentCluePosition)
//            if((currentClue)== clues.size-1){
//                holderState.isFinalClue = true
//            }
//               _uiState.emit(holderState)
//        }
        _uiState.value.isFound = true
        return true
    }
    else{
        return false
    }
}

fun advanceClue(){

     val currentCluePosition = _uiState.value.currentCluePosition
    if(currentCluePosition == clues.size-1){
        return
    }
     _uiState.value.currentClue = clues[currentCluePosition+1]
     _uiState.value.currentCluePosition = currentCluePosition+1
    if(_uiState.value.currentCluePosition == clues.size -1) _uiState.value.isFinalClue = true
    _uiState.value.isFound = false
    //_uiState.emit(_uiState.value)
}

fun displayHint(){
        uiState.value.showHint()
}
    //haversine formula
fun haversineFormulaDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val R = 6371.0 // radius
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2).pow(2.0) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) * sin(dLon / 2).pow(2.0)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return R * c
}
fun quit(){
    _uiState.value.isFound = false
    _uiState.value.currentClue = clues[0]
    _uiState.value.currentCluePosition = 0
    _uiState.value.isFinalClue = false

}

    //Lets define what functions we need
    //Like check clue + last clue
    //Change clue
    //Hint info
    //Quit button
    //timer

}