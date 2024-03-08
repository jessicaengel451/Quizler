package com.csci448.jessicaengel.quizler.presentation.question

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.csci448.jessicaengel.quizler.R

@Composable fun QuestionScoreText(score: Int){
    Log.d("448.QuestionScoreText","composing QuestionScoreText")
    Text(text= stringResource(id = R.string.label_score_formatter, score), modifier = Modifier.padding(6.dp))
}