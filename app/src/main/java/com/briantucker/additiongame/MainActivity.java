package com.briantucker.additiongame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    int locationOfCorrectAnswer;
    int score = 0;


    public void chooseAnswer(View view) {


        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
            Log.i("correct", "correct answer!");
        }
        else{
            resultTextView.setText("Wrong!");
        }

    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);

        TextView sumTextView = (TextView)findViewById(R.id.sumTextView);
        Button btn0 = (Button)findViewById(R.id.btn0);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        Button btn3 = (Button)findViewById(R.id.btn3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for (int i =0; i<4; i++){

            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {

                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b){
                    incorrectAnswer = rand.nextInt(41);
                }
                answers.add(incorrectAnswer);
            }
        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }
}
