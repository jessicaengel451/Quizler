package com.csci448.jessicaengel.quizler.data

import androidx.annotation.StringRes
import com.csci448.jessicaengel.quizler.R

class Question(@StringRes questionTextId: Int, correctChoiceNumber: Int?, choice1ID: Int, choice2ID:Int, choice3ID:Int?, choice4ID:Int?){


        public val questionTextId: Int = questionTextId
        public var correctChoiceNumber: Int? = correctChoiceNumber
        public val choice1ID:Int = choice1ID
        public val choice2ID:Int =  choice2ID
        public val choice3ID: Int? = choice3ID
        public val choice4ID: Int? = choice4ID
        public var correctAnswer: Int =  when(correctChoiceNumber){
            1-> choice1ID
            2-> choice2ID
            3 -> choice3ID!!
            4-> choice4ID !!
            else -> {0}
        }

    constructor(questionTextId: Int, trueOrFalse: Boolean) : this(questionTextId,null, R.string.label_true,R.string.label_false,null,null) {
        if(trueOrFalse){
            correctChoiceNumber = 1
        }
        else{
            correctChoiceNumber = 2
        }

        correctAnswer =  when(correctChoiceNumber){
            1-> choice1ID
            2-> choice2ID
            3 -> choice3ID!!
            4-> choice4ID !!
            else -> {0}
        }

    }


    companion object {
        //val question = Question(R.string.question1,false)
        val questions = listOf(Question(R.string.question1,false), Question(R.string.question2, true), Question(R.string.question3, true), Question(R.string.question4, false), Question(R.string.question5, true),Question(R.string.question6,3,R.string.q6_choice1,R.string.q6_choice2,R.string.q6_choice3,R.string.q6_choice4))

    }
}