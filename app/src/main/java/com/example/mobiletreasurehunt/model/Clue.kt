package com.example.mobiletreasurehunt.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
//*********
//Andrew Serrano
//Oregon State University
//CS 492- Mobile Software Development **Summer 2024
//Assignment 5: Fayetteville Treasure Hunt
//******************
data class Clue(
    @StringRes val clueName: Int,
    @StringRes val clueDescription: Int,
    val clueHint: List<Int>,
    val clueFunFact: Int,
    val clueLocation: Pair<Double, Double>,
    @DrawableRes val imageResourceID: Int,
)
