package com.example.idtyp.brain_trainer_final;

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

    Boolean gameFinished;
    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>() ;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView pointsTextView;
    TextView resultTextView;
    TextView sumTextView;
    TextView timerTextView;

    Button button1;
    Button button2;
    Button button3;
    Button button0;
    Button playAgainButton;


//    public void start(View view) {
//        startButton.setVisibility(View.INVISIBLE);
//    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        pointsTextView.setText("0/0");
        timerTextView.setText("30s");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        gameFinished = false;

        generateQuestion();
        startGame();
    }


    public void generateQuestion() {

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswer;

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {

                answers.add(a + b);
            } else {
                incorrectAnswer = rand.nextInt(41);

                while (incorrectAnswer == a + b) {
                    incorrectAnswer = rand.nextInt(41);
                }

                answers.add(incorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();
    }


    public void chooseAnswer(View view) {
        if(gameFinished){
            return;
        }
        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {

           // Log.i("Correct :", "correct");
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(score + "/" + numberOfQuestions);
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        sumTextView = findViewById(R.id.sumTextView);
        resultTextView = findViewById(R.id.resultTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        timerTextView = findViewById(R.id.timerTextView);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        gameFinished = false;
        generateQuestion();


    }

    public void goGame(View view){
        startButton.setVisibility(View.INVISIBLE);
        sumTextView.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        timerTextView.setVisibility(View.VISIBLE);

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);

        startGame();
    }

    public void startGame(){
        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {

                timerTextView.setText("0s");
                resultTextView.setText("Your Score"+ score + "/" + numberOfQuestions);
                playAgainButton.setVisibility(View.VISIBLE);
                gameFinished = true;
            }
        }.start();
    }
}

