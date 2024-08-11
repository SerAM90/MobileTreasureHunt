package com.example.mobiletreasurehunt.ui.theme

import com.example.mobiletreasurehunt.model.Clue

data class MobileUIState (
    var currentClue: Clue,
    var currentCluePosition: Int = 0,
    var currentHint: Int = 0,
    var isFound: Boolean = false,
    var isFinalClue: Boolean = false,
    var isHintDisplayed: Boolean = false
    )
    {
        fun showHint(){
        if(!isHintDisplayed) isHintDisplayed = true
        //Advance Hint
    }
    
}