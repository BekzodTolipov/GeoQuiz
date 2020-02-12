package edu.umsl.bekzod_tolipov

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cheat.*

class CheatActivity : AppCompatActivity() {

    private var isAnswerTrue = false


    companion object {
        private const val EXTRA_ANSWER_IS_TRUE = "edu.umsl.bekzod_tolipov.answer_is_true"
        private const val EXTRA_ANSWER_SHOWN = "edu.umsl.bekzod_tolipov.answer_shown"

        @JvmStatic
        fun newIntent(context: Context, answerIsTrue: Boolean): Intent {
            val intent = Intent(context, CheatActivity::class.java)
            intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            return intent
        }

        fun wasAnswerShown(intent: Intent): Boolean {
            return intent.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        isAnswerTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        show_answer_btn.setOnClickListener {
            if (isAnswerTrue) {
                answer_text_view.setText(R.string.true_text)
            } else {
                answer_text_view.setText(R.string.false_text)
            }
            val intent = Intent()
            intent.putExtra(EXTRA_ANSWER_SHOWN, true)
            setResult(Activity.RESULT_OK, intent)
        }
    }

//    private fun setAnswerShownResult(answerShown: Boolean) {
//
//    }
}
