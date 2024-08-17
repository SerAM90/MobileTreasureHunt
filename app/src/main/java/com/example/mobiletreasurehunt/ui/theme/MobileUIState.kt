package com.example.mobiletreasurehunt.ui.theme

import com.example.mobiletreasurehunt.model.Clue
//*********
//Andrew Serrano
//Oregon State University
//CS 492- Mobile Software Development **Summer 2024
//Assignment 5: Fayetteville Treasure Hunt
//******************
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