package com.example.mobiletreasurehunt.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Clue(
    @StringRes val clueName: Int,
    @StringRes val clueDescription: Int,
    val clueHint: List<Int>,
    val clueFunFact: Int,
    val clueLocation: Pair<Double, Double>,
    @DrawableRes val imageResourceID: Int,
)
