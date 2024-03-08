package com.csci448.jessicaengel.quizler.presentation.navigation.specs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.jessicaengel.quizler.presentation.cheat.CheatScreen
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuestionStatus
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModel

object CheatScreenSpec: IScreenSpec {
    override val route = "cheat"

    @Composable override fun Content(quizlerViewModel: QuizlerViewModel , navController: NavController){
        CheatScreen(quizlerViewModel = quizlerViewModel) {
            if (quizlerViewModel.currentQuestionStatus.value == QuestionStatus.UNANSWERED){
                quizlerViewModel.cheated()
            }
             }//{
////            if (quizlerViewModel.currentQuestionStatus == QuestionStatus.UNANSWERED){
////                quizlerViewModel.cheated()
////            }
//        }
    }
}