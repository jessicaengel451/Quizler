package com.csci448.jessicaengel.quizler.presentation.question

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.csci448.jessicaengel.quizler.R
import com.csci448.jessicaengel.quizler.data.Question
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModel

@Composable fun QuestionScreen(quizlerViewModel: QuizlerViewModel, onCheat: () -> Unit){

    val orientation = LocalConfiguration.current.orientation
    val defaultButtonColors = ButtonDefaults.buttonColors()
    val currentContext = LocalContext.current
    @Composable
    fun nextOrPreviousButton(moveTo: () -> Unit, labelId:Int) {
        QuestionButton(buttonText = stringResource(id = labelId),
            { moveTo() }, true, defaultButtonColors
        )
    }
    Log.d("448.QuestionDisplay","composing QuestionScreen")
    when (orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                QuestionScoreText(quizlerViewModel.currentScoreState.value)
                Box(modifier=Modifier.fillMaxHeight(.80f)){
                    QuestionDisplay(
                        question = quizlerViewModel.mCurrentQuestionState.value,
                        onCorrectAnswer =
                        {
                            quizlerViewModel.answeredCorrect()
                            Toast.makeText(currentContext, R.string.message_correct, Toast.LENGTH_SHORT)
                                .show()
                        },
                        onWrongAnswer = {
                            quizlerViewModel.answeredIncorrect()
                            Toast.makeText(currentContext, R.string.message_wrong, Toast.LENGTH_SHORT)
                                .show()
                        }, onCheatAnswer = {
                            quizlerViewModel.cheated()
                            Toast.makeText(currentContext, "Cheaters never prosper.", Toast.LENGTH_SHORT) .show()
                        },
                        quizlerViewModel.currentQuestionStatus,
                        orientation
                    )
                }

                Box(modifier = Modifier.fillMaxHeight(.2f))
                Row() {
                    Box(
                        modifier = Modifier
                            .weight(.5f)
                    ) {

                        nextOrPreviousButton(
                            { quizlerViewModel.moveToPreviousQuestion() },
                            R.string.label_previous
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(.5f)
                    ) {
                        nextOrPreviousButton(
                            { quizlerViewModel.moveToNextQuestion() },
                            R.string.label_next
                        )
                    }
                    //}

                }
            }
            // create landscape composable tree here
        }
        else -> {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(){
                    Button(onClick = {onCheat()}){
                        Text("Cheat!")
                    }
                    QuestionScoreText(quizlerViewModel.currentScoreState.value)
                }

                QuestionDisplay(question = quizlerViewModel.mCurrentQuestionState.value, onCorrectAnswer =
                { quizlerViewModel.answeredCorrect()
                    Toast.makeText(currentContext, R.string.message_correct, Toast.LENGTH_SHORT) .show() },
                    onWrongAnswer = {
                        quizlerViewModel.answeredIncorrect()
                        Toast.makeText(currentContext, R.string.message_wrong, Toast.LENGTH_SHORT) .show()
                    }, onCheatAnswer = {
                                       quizlerViewModel.answeredCheated()
                        Toast.makeText(currentContext, "Cheaters never prosper.", Toast.LENGTH_SHORT) .show()
                    },quizlerViewModel.currentQuestionStatus,orientation)

                // create portrait composable tree here } }
                Row() {
                    Box(
                        modifier = Modifier
                            .weight(.5f)
                    ) {

                        nextOrPreviousButton({quizlerViewModel.moveToPreviousQuestion()},R.string.label_previous)
                    }

                    Box(
                        modifier = Modifier
                            .weight(.5f)
                    ) {
                        nextOrPreviousButton({quizlerViewModel.moveToNextQuestion()},R.string.label_next)
                    }
                }
            }


        }
    }
}
@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable fun PreviewQuestionScreen(){
    QuestionScreen(QuizlerViewModel(Question.questions)) {}
}