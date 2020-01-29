package edu.umsl.bekzod_tolipov;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private QuizModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionTextView = findViewById(R.id.questionTextView);

        Integer index = 0;
        if(savedInstanceState != null){
            index = savedInstanceState.getInt("QUESTION_INDEX", 0);
        }

        model = new QuizModel(index);
        questionTextView.setText(model.getQuestionAtIndex().getId());
//        Button trueButton = findViewById(R.id.trueButton.);
        Button falseButton = findViewById(R.id.falseButton);

//        trueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

//        falseButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        falseButton.setOnClickListener(falseListener);
    }

    //Preferred way
    public void clickedTrue(View trueButton){
        Log.d("Tag", "Clicked the true button");
        model.increment();
        questionTextView.setText(model.getQuestionAtIndex().getId());
    }

    View.OnClickListener falseListener = new View.OnClickListener(){
      @Override
      public void onClick(View v){
          Log.d("TAG", "Clicked th false button");
      }
    };

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("QUESTION_INDEX", model.getCurrentIndex());
    }
}
