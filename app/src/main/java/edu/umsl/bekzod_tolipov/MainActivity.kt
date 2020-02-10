package edu.umsl.bekzod_tolipov

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var model: QuizModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {bundle ->
            val index = bundle.getInt("QUESTION_INDEX", 0)
            model = QuizModel(index)
        }
        if (!::model.isInitialized){
            model = QuizModel(0)
        }

        trueButton.setOnClickListener(selectionListener)
        falseButton.setOnClickListener(selectionListener)
        questionTextView.setText(model.currentQuestion.id)

        cheatButton.setOnClickListener {
            val isTrue = model.currentQuestion.isTrue
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    var selectionListener= View.OnClickListener { v ->
        var selectedValue = false
        if (v.id == R.id.trueButton){
            selectedValue = true
        }

        if (model.isAnswerCorrect(selectedValue)){
            Toast.makeText(this@MainActivity,
                    R.string.correct_toast,
                    Toast.LENGTH_SHORT)
                            .show()
        }
        else{
            Toast.makeText(this@MainActivity,
                    R.string.incorrect_toast,
                    Toast.LENGTH_SHORT)
                            .show()
        }

        model.advanceToNextQuestion()
        questionTextView.setText(model.currentQuestion.id)
    }

   override fun onSaveInstanceState(outState: Bundle): Unit {
       super.onSaveInstanceState(outState)
       outState.putInt("QUESTION_INDEX", model.currentIndex)
   }
}