package com.csci448.jessicaengel.quizler.presentation.viewmodel

import android.util.Log
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.jessicaengel.quizler.data.Question
import com.csci448.jessicaengel.quizler.ui.theme.Blue20
import com.csci448.jessicaengel.quizler.ui.theme.Gold60
import com.csci448.jessicaengel.quizler.ui.theme.Red40

class QuizlerViewModel(private val mQuestions: List<Question>,

                       private var mCurrentQuestionIndex: Int = 0, initialScore: Int = 0) : ViewModel() {
    private val mCurrentScoreState = mutableStateOf(initialScore)
    init{
        Log.d("448.QuestionViewModel", "ViewModel created")
    }

    private var questionsStatus = MutableList(mQuestions.size) {QuestionStatus.UNANSWERED}

    val currentQuestionStatus: MutableState<QuestionStatus> = mutableStateOf(questionsStatus[mCurrentQuestionIndex])

//    val currentQuestionStatus: MutableState<QuestionStatus>
//        get()= mutableStateOf(questionsStatus[mCurrentQuestionIndex])
    //private var mCurrentQuestionIndex: MutableState<Int> = mutableStateOf(0)
    val mCurrentQuestionState = mutableStateOf( mQuestions[mCurrentQuestionIndex] )

    var currentScoreState: MutableState<Int> = mutableStateOf(0)

    fun moveToNextQuestion(){
        if(mCurrentQuestionIndex==mQuestions.size-1){
            mCurrentQuestionIndex = 0
        }
        else{
            mCurrentQuestionIndex = mCurrentQuestionIndex+1
        }
        refreshQuestion()
    }

    fun moveToPreviousQuestion(){
        if(mCurrentQuestionIndex==0){
            mCurrentQuestionIndex =mQuestions.size-1
        }
        else{
            mCurrentQuestionIndex = mCurrentQuestionIndex-1
        }
        refreshQuestion()
    }

    fun refreshQuestion(){
        mCurrentQuestionState.value=mQuestions[mCurrentQuestionIndex]
        currentQuestionStatus.value=questionsStatus[mCurrentQuestionIndex]
    }

    fun answeredCorrect(){
        currentScoreState.value++
        questionsStatus[mCurrentQuestionIndex]=QuestionStatus.ANSWERED_CORRECT
        //currentQuestionStatus.value = QuestionStatus.ANSWERED_CORRECT
        updateCurrentQuestionStatus(QuestionStatus.ANSWERED_CORRECT)
        refreshQuestion()
    }

    fun answeredIncorrect(){
        //currentScoreState.value++
        questionsStatus[mCurrentQuestionIndex]=QuestionStatus.ANSWERED_INCORRECT
        updateCurrentQuestionStatus(QuestionStatus.ANSWERED_INCORRECT)
        refreshQuestion()
    //currentQuestionStatus.value = QuestionStatus.ANSWERED_INCORRECT
    }

    fun updateCurrentQuestionStatus(questionStatus: QuestionStatus){
        currentQuestionStatus.value = questionStatus
    }

    fun cheated(){
        questionsStatus[mCurrentQuestionIndex]=QuestionStatus.CHEATED
        updateCurrentQuestionStatus(QuestionStatus.CHEATED)
    }

//    fun answeredCheated(){
//
//    }
    fun answeredCheated(){
    questionsStatus[mCurrentQuestionIndex]=QuestionStatus.ANSWER_CHEATED
    updateCurrentQuestionStatus(QuestionStatus.ANSWER_CHEATED)
    }


    val currentQuestionState: State<Question>
        get() = mCurrentQuestionState

    val currentQuestionIndex: Int get() = mCurrentQuestionIndex
}