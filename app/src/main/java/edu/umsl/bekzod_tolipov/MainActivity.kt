package edu.umsl.bekzod_tolipov

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private lateinit var model: QuizModel
    private var isCheater: Boolean = false

    companion object{
        const val REQUEST_CODE_CHEAT = 0
        const val POSITION_KEY = "position"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {bundle ->
            val index = bundle.getInt(POSITION_KEY, 0)
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
            val intent = CheatActivity.newIntent(this, isTrue)
 //           startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (data == null) { return }

        when (requestCode) {
            REQUEST_CODE_CHEAT -> {
                this.isCheater = CheatActivity.wasAnswerShown(data)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle): Unit {
        super.onSaveInstanceState(outState)
        outState.putInt("QUESTION_INDEX", model.currentIndex)
    }

    private var selectionListener = object : View.OnClickListener {
        override fun onClick(v: View) {
            var selectedValue = false
            if (v.id == R.id.trueButton) {
                selectedValue = true
            }

            val messageResId = if (isCheater) {
                R.string.judgment_toast
            } else {
                if (model.isAnswerCorrect(selectedValue)) {
                    R.string.correct_toast
                } else {
                    R.string.incorrect_toast
                }
            }

            Toast.makeText(this@MainActivity,
                    messageResId,
                    Toast.LENGTH_SHORT)
                    .show()

            isCheater = false
            model.advanceToNextQuestion()
            questionTextView.setText(model.currentQuestion.id)
        }
    }


}