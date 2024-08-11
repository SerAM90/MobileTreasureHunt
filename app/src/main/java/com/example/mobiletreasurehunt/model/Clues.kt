package com.example.mobiletreasurehunt.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Clues(
    @StringRes val clueName: Int,
    @StringRes val clueHint: Int,
    val clueLocation: Pair<Double, Double>,
    @DrawableRes val imageResourceID: Int,
)
