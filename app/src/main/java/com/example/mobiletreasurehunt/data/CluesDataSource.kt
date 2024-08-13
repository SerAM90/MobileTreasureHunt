package com.example.mobiletreasurehunt.data
import com.example.mobiletreasurehunt.R
import com.example.mobiletreasurehunt.model.Clue

class CluesDataSource() {
    fun loadClues(): List<Clue>{
        return listOf(
            Clue(
                clueName = R.string.circa1800,
                clueDescription = R.string.clue1_circa1800,
                clueHint = listOf(R.string.hint1_circa1800), //R.string.hint1_circa1800,
                clueFunFact = R.string.finalcirca1800,
                clueLocation = Pair(35.0507, -78.8863), //coordinates for Circa 1800
                imageResourceID = R.drawable.circa1800
            ),
            Clue(
                clueName = R.string.sweetpalette,
                clueDescription = R.string.clue2_sweetpalette,
                clueHint = listOf(R.string.hint1_sweetpalette),
                clueFunFact = R.string.finalsweetpalette,
                clueLocation = Pair(35.0535, -78.8837), //coordinates for The Sweet Palette
                imageResourceID = R.drawable.thesweetpalette
            )
        )
    }
}
