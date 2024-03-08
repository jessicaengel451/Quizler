package com.csci448.jessicaengel.quizler.presentation.question

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.jessicaengel.quizler.data.Question
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuestionStatus
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuestionStatus.*
import com.csci448.jessicaengel.quizler.ui.theme.Blue20
import com.csci448.jessicaengel.quizler.ui.theme.Gold60
import com.csci448.jessicaengel.quizler.ui.theme.Red40

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun QuestionDisplay(question: Question, onCorrectAnswer:() -> Unit, onWrongAnswer:() -> Unit, onCheatAnswer:()->Unit, currentQuestionStatus: MutableState<QuestionStatus>, orientation:Int){

    //val defaultColor = ButtonDefaults.buttonColors()

    val correctButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Gold60,
        disabledContentColor = Blue20,
    )

    // Define the incorrect button colors
    val incorrectButtonColors = ButtonDefaults.buttonColors(
        disabledContainerColor = Red40,
        disabledContentColor = Gold60,
    )

    // Define the default button colors
    val defaultButtonColors = ButtonDefaults.buttonColors()
    when (orientation) { Configuration.ORIENTATION_LANDSCAPE -> {
    // create landscape composable tree here
            Row(){
                Box(modifier = Modifier.weight(.75f)){
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 50.dp)
                                    .padding(top = 50.dp)
                            ) {
                                Text(
                                    text = stringResource(id = question.questionTextId),
                                    fontSize = 28.sp,
                                    color = MaterialTheme.colorScheme.primary,
                                    style = TextStyle(lineHeight = 40.sp)

                                )
                            }

                        }
                    }
                }

                Box(modifier = Modifier.weight(.25f)){
                    Column(){


                        QuestionButton(buttonText = stringResource(id = question.choice1ID),
                            onButtonClick = { question.correctChoiceNumber?.let {
                                if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                else{checkAnswerChoice(1,
                                    it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}

                            } }
                            ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                ANSWERED_CORRECT -> correctButtonColors
                                ANSWERED_INCORRECT -> incorrectButtonColors
                                UNANSWERED -> defaultButtonColors
                                ANSWER_CHEATED -> incorrectButtonColors
                                else -> {defaultButtonColors}
                            })


                            QuestionButton(buttonText = stringResource(id = question.choice2ID),
                                onButtonClick =
                                { question.correctChoiceNumber?.let {
                                    if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                    else{ checkAnswerChoice(2,
                                        it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()}) }}

                                }
                                , enabled = (currentQuestionStatus.value== UNANSWERED || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                    ANSWERED_CORRECT -> correctButtonColors
                                    ANSWERED_INCORRECT -> incorrectButtonColors
                                    UNANSWERED -> defaultButtonColors
                                    ANSWER_CHEATED -> incorrectButtonColors
                                    else -> {incorrectButtonColors}
                                })


                            question.choice3ID?.let { stringResource(id = it) }
                                ?.let { QuestionButton(buttonText = it, onButtonClick = { question.correctChoiceNumber?.let {
                                    if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                    else{ checkAnswerChoice(3,
                                        it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}

                                } }
                                    ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                        ANSWERED_CORRECT -> correctButtonColors
                                        ANSWERED_INCORRECT -> incorrectButtonColors
                                        UNANSWERED -> defaultButtonColors
                                        ANSWER_CHEATED -> incorrectButtonColors
                                        else -> {defaultButtonColors}
                                    })



                            question.choice4ID?.let { stringResource(id = it) }
                                ?.let { QuestionButton(buttonText = it, onButtonClick = { question.correctChoiceNumber?.let {
                                    if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                    else{checkAnswerChoice(4,
                                        it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}

                                } }
                                    ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                        ANSWERED_CORRECT -> correctButtonColors
                                        ANSWERED_INCORRECT -> incorrectButtonColors
                                        UNANSWERED -> defaultButtonColors
                                        ANSWER_CHEATED -> incorrectButtonColors
                                        else -> {incorrectButtonColors}
                                    })  }
}

                        //TODO more questions
                    }
                }

            }
        }
        else -> {
            Column() {

                QuestionTextCard(question)

                Row() {
                    Box(modifier = Modifier
                        .height(50.dp)
                        .weight(.5f))
                    {
                        QuestionButton(buttonText = stringResource(id = question.choice1ID),
                            onButtonClick = { question.correctChoiceNumber?.let {
                                if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                else{checkAnswerChoice(1,
                                    it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}

                            } }
                            ,enabled = (currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),

                            when (currentQuestionStatus.value){
                                ANSWERED_CORRECT -> correctButtonColors
                                ANSWERED_INCORRECT -> incorrectButtonColors
                                UNANSWERED -> defaultButtonColors
                                ANSWER_CHEATED -> incorrectButtonColors
                                else -> {incorrectButtonColors}
                            }
                        )
                    }
                    Box(modifier = Modifier
                        .height(50.dp)
                        .weight(.5f))
                    {
                        QuestionButton(buttonText = stringResource(id = question.choice2ID),
                            onButtonClick =
                            { question.correctChoiceNumber?.let {
                                if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                else{checkAnswerChoice(2,
                                    it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()}) }}

                            }
                            ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                ANSWERED_CORRECT -> correctButtonColors
                                ANSWERED_INCORRECT -> incorrectButtonColors
                                UNANSWERED -> defaultButtonColors
                                ANSWER_CHEATED -> incorrectButtonColors
                                else -> {defaultButtonColors}
                            })  }


                }
                Row(){
                    Box(modifier = Modifier
                        .height(50.dp)
                        .weight(.5f))
                    {
                        question.choice3ID?.let { stringResource(id = it) }
                            ?.let { QuestionButton(buttonText = it, onButtonClick = { question.correctChoiceNumber?.let {
                                if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                else{ checkAnswerChoice(3,
                                    it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}

                            } }
                                ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                    ANSWERED_CORRECT -> correctButtonColors
                                    ANSWERED_INCORRECT -> incorrectButtonColors
                                    UNANSWERED -> defaultButtonColors
                                    ANSWER_CHEATED -> incorrectButtonColors
                                    else -> {defaultButtonColors}
                                })  }
                    }

                    Box(modifier = Modifier
                        .height(50.dp)
                        .weight(.5f))
                    {
                        question.choice4ID?.let { stringResource(id = it) }
                            ?.let { QuestionButton(buttonText = it, onButtonClick = { question.correctChoiceNumber?.let {
                                if(currentQuestionStatus.value== CHEATED){onCheatAnswer()}
                                else{checkAnswerChoice(4,
                                    it, correctAnswer = {onCorrectAnswer()}, wrongAnswer = {onWrongAnswer()})}
                            } }
                                ,enabled =(currentQuestionStatus.value== UNANSWERED  || currentQuestionStatus.value== CHEATED),when (currentQuestionStatus.value){
                                    ANSWERED_CORRECT -> correctButtonColors
                                    ANSWERED_INCORRECT -> incorrectButtonColors
                                    UNANSWERED -> defaultButtonColors
                                    ANSWER_CHEATED -> incorrectButtonColors
                                    else -> {incorrectButtonColors}
                                })  }
                    }
                }
            }
        }
        // create portrait composable tree here }
    }
    Log.d("448.QuestionDisplay","composing ${stringResource(id = question.questionTextId)}")


}

private fun checkAnswerChoice(chosen: Int, correct:Int, correctAnswer:()->Unit, wrongAnswer:()->Unit){
    if(chosen == correct){
        correctAnswer()
        //usedColor = correctButtonColors
    }
    else{
        wrongAnswer()
        //usedColor = incorrectButtonColors
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun QuestionTextCard(question: Question) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp)
                    .padding(top = 150.dp)
            ) {
                Text(
                    text = stringResource(id = question.questionTextId),
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary,
                    style = TextStyle(lineHeight = 40.sp)

                )
            }

        }
    }
}



@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=portrait")
@Composable fun PreviewPortraitQuestionDisplay()
{
    val status = remember { mutableStateOf(UNANSWERED) }
    QuestionDisplay(question = Question.questions[5], { } , { }, {},
        status ,Configuration.ORIENTATION_PORTRAIT)
}

@Preview(showBackground = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable fun PreviewLandscapeQuestionDisplay()
{
    val status = remember { mutableStateOf(UNANSWERED) }
    QuestionDisplay(question = Question.questions[5], { } , { }, {},
        status ,Configuration.ORIENTATION_LANDSCAPE)
}