package com.example.mobiletreasurehunt

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.mobiletreasurehunt.data.Routes
import com.example.mobiletreasurehunt.model.Clue
import com.example.mobiletreasurehunt.ui.theme.MobileViewModel

@Composable
fun TreasureHuntCompletedPage(
    navController: NavHostController,
    mobileViewModel: MobileViewModel
){
    val uiState by mobileViewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier) {
        TreasureHuntCompletedCard(clue = uiState.currentClue, navController = navController,mobileViewModel = mobileViewModel, modifier = Modifier)
    }

}

@Composable
fun TreasureHuntCompletedCard(clue: Clue, navController: NavHostController, mobileViewModel: MobileViewModel, modifier: Modifier){
    Column(modifier = modifier) {
        Text(
            text ="Congratulations!" )
        Image(painter = painterResource(id = clue.imageResourceID), contentDescription = stringResource(clue.clueName
        ) )
        Text(
            text = stringResource(R.string.congratulations))
        Text(
            text = "Elapsed time: "
        )
        Text(
            text = stringResource(clue.clueFunFact))
        Button(
            onClick = {
                mobileViewModel.quit()
                navController.navigate(Routes.StartPage)
            }
        ) {
            Text(text = "Home")
        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun TreasureHuntCompletedCardPreview() {
//    TreasureHuntCompletedCard(
//        clue = Clue(
//            clueName = R.string.circa1800,
//            clueDescription = R.string.clue1_circa1800,
//            clueHint = listOf(R.string.hint1_circa1800),
//            clueFunFact = R.string.finalcirca1800,
//            clueLocation = Pair(35.0507, -78.8863),
//            imageResourceID = R.drawable.circa1800
//        ),
//        navController = rememberNavController(),
//        modifier = Modifier
//    )
//}