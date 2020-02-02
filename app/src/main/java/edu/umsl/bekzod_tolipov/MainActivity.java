package edu.umsl.bekzod_tolipov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private QuizModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);

        int index = 0;
        if(savedInstanceState != null){
            index = savedInstanceState.getInt("QUESTION_INDEX", 0);
        }

        model = new QuizModel(index);
        questionTextView.setText(model.getCurrentQuestion().getId());

        Button trueButton = findViewById(R.id.trueButton);
        Button falseButton = findViewById(R.id.falseButton);

        trueButton.setOnClickListener(userSelectionListener);
        falseButton.setOnClickListener(userSelectionListener);

    }

    View.OnClickListener userSelectionListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            boolean selectedValue = false;
            if (v.getId() == R.id.trueButton){
                selectedValue = true;
            }

            if(model.isAnswerCorrect(selectedValue)) {
                Toast.makeText(MainActivity.this,
                        R.string.correct_toast,
                        Toast.LENGTH_SHORT)
                        .show();
            }else{
                Toast.makeText(MainActivity.this,
                        R.string.incorrect_toast,
                        Toast.LENGTH_SHORT)
                        .show();
            }
            model.advanceToNextQuestion();
            questionTextView.setText(model.getCurrentQuestion().getId());
        }
    };

    //Preferred way
//    public void clickedTrue(View view){
//        Log.d("Tag", "Clicked the true button");
//        if(model.isAnswerCorrect(true)) {
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT)
//                    .show();
//        }else{
//            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }

//    View.OnClickListener truePressed = new View.OnClickListener(){
//      @Override
//      public void onClick(View v){
//          model.isAnswerCorrect();
//      }
//    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QUESTION_INDEX", model.getCurrentIndex());
    }
}
