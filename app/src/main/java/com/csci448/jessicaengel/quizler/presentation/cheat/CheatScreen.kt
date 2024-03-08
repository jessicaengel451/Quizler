package com.csci448.jessicaengel.quizler.presentation.cheat

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.jessicaengel.quizler.data.Question.Companion.questions
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuestionStatus
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModel

@Composable
fun CheatScreen(quizlerViewModel: QuizlerViewModel, onButtonClick: () -> Unit){
    Column() {
        Text("Are you sure you want to cheat?")
        Button(onClick = { onButtonClick() }){
            Text("Cheat!")
        }
        if(quizlerViewModel.currentQuestionStatus.value == QuestionStatus.CHEATED){
            val x: Int = questions[0].correctAnswer
            Text(stringResource(id = questions[quizlerViewModel.currentQuestionIndex].correctAnswer))
        }
    }
}

//@Preview
//@Composable fun previewCheatScreen(){
//    CheatScreen(null)
//}