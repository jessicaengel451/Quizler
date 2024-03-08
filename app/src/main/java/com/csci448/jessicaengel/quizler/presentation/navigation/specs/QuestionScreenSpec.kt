package com.csci448.jessicaengel.quizler.presentation.navigation.specs
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.jessicaengel.quizler.presentation.question.QuestionScreen
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModel

object QuestionScreenSpec: IScreenSpec {
    override val route: String = "question"
        //get() = TODO("Not yet implemented")
    @Composable
        override fun Content(quizlerViewModel: QuizlerViewModel, navController: NavController) {
            QuestionScreen(quizlerViewModel =quizlerViewModel){
                navController.navigate("cheat")
            }
        //QuestionScreen(quizlerViewModel)
        }
}