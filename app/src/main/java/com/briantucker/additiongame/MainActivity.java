package com.briantucker.additiongame;

import android.os.CountDownTimer;
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
    Button btn0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button playAgainBtn;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    TextView resultTextView;
    TextView ptsTextView;
    TextView sumTextView;
    TextView timerTextView;

    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;




    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        ptsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainBtn.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(3100, 1000) {
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) +"s");
            }
            @Override
            public void onFinish() {
                playAgainBtn.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText(("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions)));
            }
        }.start();

    }

    public void generateQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;

        //this is used to display the numbers on the grid randomly, along with one being the correct number
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


    public void chooseAnswer(View view) {


        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Correct!");
        }
        else{
            resultTextView.setText("Wrong!");
        }

        numberOfQuestions++;
        ptsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();
    }

    public void start(View view){

        startButton.setVisibility(View.INVISIBLE);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        ptsTextView = (TextView) findViewById(R.id.ptsTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        playAgainBtn = (Button)findViewById(R.id.playAgainBtn);

        //needed a view or an error, any view will do
        playAgain(findViewById(R.id.playAgainBtn));


    }
}
