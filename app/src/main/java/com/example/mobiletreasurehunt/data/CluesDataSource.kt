package com.example.mobiletreasurehunt.data
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.model.Clues

class CluesDataSource() {
    fun loadClues(): List<Clues>{
        return listOf(
            Clues(
                clueName = R.string.clue1_circa1800,
                clueHint = R.string.hint1_circa1800,
                clueLocation = Pair(35.0507, -78.8863), //coordinates for Circa 1800
                imageResourceID = R.drawable.circa1800
            ),
            Clues(
                clueName = R.string.clue2_sweetpalette,
                clueHint = R.string.hint1_sweetpalette,
                clueLocation = Pair(35.0535, -78.8837), //coordinates for The Sweet Palette
                imageResourceID = R.drawable.thesweetpalette
            )
        )
    }
}
