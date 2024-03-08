package com.csci448.jessicaengel.quizler

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.jessicaengel.quizler.presentation.navigation.QuizlerNavHost
import com.csci448.jessicaengel.quizler.presentation.question.QuestionScreen
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModel
import com.csci448.jessicaengel.quizler.presentation.viewmodel.QuizlerViewModelFactory
import com.csci448.jessicaengel.quizler.ui.theme.QuizlerTheme

class MainActivity : ComponentActivity() {
    private lateinit var mViewModel: QuizlerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        val initialIndex = savedInstanceState?.getInt("index", 0) ?: 0
        val initialScore = savedInstanceState?.getInt("score", 0) ?: 0

//        else{
//            savedInstanceState.putString("score", "0")
//            savedInstanceState.putString("index","0")
//        }

        val factory = QuizlerViewModelFactory(initialIndex, initialScore)
        mViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        Log.d("448.MainActivity", "onCreate() called")
        super.onCreate(savedInstanceState)
        setContent {
            QuizlerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    QuizlerNavHost(navController = navController, quizlerViewModel = mViewModel)
                    //QuestionScreen( mViewModel)
                }
            }
        }
    }

    override fun onSaveInstanceState(bundle: Bundle){
        bundle.putString("score", mViewModel.currentScoreState.toString())
        bundle.putString("index",mViewModel.currentQuestionIndex.toString())
        super.onSaveInstanceState(bundle)
    }
    override fun onStart() {
        super.onStart()
        Log.d("448.MainActivity", "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("448.MainActivity", "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("448.MainActivity", "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("448.MainActivity", "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("448.MainActivity", "onDestroy called")
    }

    override fun onContentChanged() {
        super.onContentChanged()
        Log.d("MyActivity", "Content changed")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d("MyActivity", "onPostCreate")
    }

    override fun onPostResume() {
        super.onPostResume()
        Log.d("MyActivity", "onPostCreate")
    }

    override fun onAttachedToWindow(){
        super.onAttachedToWindow()
        Log.d("MyActivity", "onAttachedToWindow")
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        Log.d("MyActivity", "onEnterAnimationComplete")
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d("MyActivity", "onDetachedFromWindow")
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuizlerTheme {
        Greeting("Android")
    }
}