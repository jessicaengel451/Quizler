package com.csci448.jessicaengel.quizler.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.jessicaengel.quizler.data.Question

class QuizlerViewModelFactory(initialIndex: Int, initialScore: Int) : ViewModelProvider.NewInstanceFactory() {
    private val initialIndex = 0
    private val initialScore = 0

//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        Log.d("448.QuestionViewModel", "Creating $modelClass")
//        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
//            return QuestionViewModel as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    Log.d("448.ViewModel", "Creating $modelClass")
    if( modelClass.isAssignableFrom(getViewModelClass()) )
        return modelClass
            .getConstructor(List::class.java, Int::class.java, Int::class.java).newInstance(Question.questions, initialIndex, initialScore)
    throw IllegalArgumentException("Unknown ViewModel")
    }

    fun getViewModelClass() = QuizlerViewModel::class.java

    companion object {
        fun getViewModelClass() = QuizlerViewModel::class.java
        private var index: String = "index"
        private var score: String ="score"
    }

}