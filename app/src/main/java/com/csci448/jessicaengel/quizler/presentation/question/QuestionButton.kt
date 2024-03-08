package com.csci448.jessicaengel.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.jessicaengel.quizler.data.Question.Companion.questions
import kotlinx.coroutines.delay

@Composable fun QuestionButton(buttonText: String, onButtonClick: () -> Unit, enabled:Boolean, colors:ButtonColors){
    val defaultColor = ButtonDefaults.buttonColors()
    var isClicked by remember { mutableStateOf(false) }
    Log.d("448.QuestionButton","composing questions button")
    ElevatedButton(onClick = {isClicked = true
        onButtonClick()}
        , modifier = Modifier
        .padding(6.dp)
        .fillMaxWidth(), enabled=enabled,
        colors = if(isClicked) colors else defaultColor, elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp, pressedElevation = 8.dp,focusedElevation = 2.dp, hoveredElevation = 2.dp, disabledElevation = 0.dp)
    )
    {
//        LaunchedEffect(isClicked) {
            if (isClicked) {
//                delay(2000)
                isClicked = false
            }
        //}
        Text(text=buttonText)
    }

}

@Preview
@Composable fun PreviewQuestionButton(){
    questions[5].choice3ID?.let { stringResource(id = it) }?.let { QuestionButton(buttonText = it,{},enabled=true, colors = ButtonDefaults.buttonColors(
        contentColor = Color.Red)) }


//    QuestionButton(buttonText = "test") {
//
//    }
}