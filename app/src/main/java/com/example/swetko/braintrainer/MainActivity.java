package com.example.swetko.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int result;
    int correctAnswerPosition;
    int scores;
    int trys;
    Button buttonStart;
    TextView textViewMathProblem;
    TextView textViewIsCorrect;
    TextView textViewTimer;
    TextView textViewScore;
    GridLayout gLayout;
    CountDownTimer countDownTimer;
    Boolean gameState = false;

    public void reset() {

    }

    public void answer(View view) {
        Button buttonAnswers = (Button) view;
        String message;

        if (Integer.parseInt(buttonAnswers.getTag().toString()) == correctAnswerPosition) {
            message = "CORRECT";
            scores++;
            trys++;
        } else {
            message = "INCORRECT";
            trys++;
        }

        textViewIsCorrect.setText(message);
        textViewIsCorrect.setVisibility(View.VISIBLE);
        textViewScore.setText(Integer.toString(scores) + "/" + Integer.toString(trys));
        setRandomMarProblem();
    }

    public void startBrainTriner(View view) {
        gameState = true;
        gLayout.setVisibility(View.VISIBLE);
        textViewMathProblem.setVisibility(View.VISIBLE);
        textViewTimer.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);
        buttonStart.setVisibility(View.VISIBLE);
        trys = 0;
        scores = 0;
        textViewScore.setText(Integer.toString(scores) + "/" + Integer.toString(trys));
        setRandomMarProblem();
        buttonStart.setVisibility(View.INVISIBLE);


        countDownTimer = new CountDownTimer(30000 + 100, 1000) {

            public void onTick(long millisecondsUntilDone) {
                textViewTimer.setText(String.valueOf(millisecondsUntilDone / 1000));
            }

            public void onFinish() {
                textViewTimer.setText("0");
                gLayout.setVisibility(View.INVISIBLE);
                textViewMathProblem.setVisibility(View.INVISIBLE);
                textViewTimer.setVisibility(View.INVISIBLE);
                textViewScore.setVisibility(View.INVISIBLE);
                buttonStart.setVisibility(View.VISIBLE);
                buttonStart.setText("Try again");
                textViewIsCorrect.setText("Score: " + Integer.toString(scores) + "/" + Integer.toString(trys));
            }
        }.start();

    }

    public void setRandomMarProblem() {
        int max = 20;
        int a = setRandomNumber(max);
        int tmpX = max - a;
        int b = setRandomAnwser(1, tmpX);
        result = a + b;
        correctAnswerPosition = setRandomNumber(4);

        textViewMathProblem.setText(Integer.toString(a) + " + " + Integer.toString(b));

        GridLayout gLayout = findViewById(R.id.gridLayout);
        for(int i=0; i<gLayout.getChildCount(); i++) {
            Button counter = (Button) gLayout.getChildAt(i);

            if (i == correctAnswerPosition) {
                counter.setText(Integer.toString(result));
            } else {
                int tmpMin;
                int tmpMax = result + 20;
                if (tmpMax >= max) {
                    tmpMax = max;
                }
                if (a <= b) {
                    tmpMin = b;
                    int tmpResult;
                    do {
                        tmpResult = setRandomAnwser(tmpMin, tmpMax);
                    } while (tmpResult == result);

                    counter.setText(Integer.toString(tmpResult));
                } else {
                    tmpMin = a;
                    int tmpResult;
                    do {
                        tmpResult = setRandomAnwser(tmpMin, tmpMax);
                    } while (tmpResult == result);

                    counter.setText(Integer.toString(tmpResult));
                    //counter.setText(Integer.toString(setRandomAnwser(tmpMin, max)));
                }
                //counter.setText(Integer.toString(setRandomAnwser()));
            }
        }
    }

    public int setRandomAnwser(int min, int max) {
        Random randm = new Random();
        int tmp = randm.nextInt((max - min) + 1) + min;
        return tmp;
    }

    public int setRandomNumber(int x) {
        Random randm = new Random();
        int rnm = randm.nextInt(x);
        return rnm;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonSart);
        textViewMathProblem = findViewById(R.id.textViewMathProblem);
        textViewIsCorrect = findViewById(R.id.textViewIsCorrect);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewScore = findViewById(R.id.textViewScore);
        gLayout = findViewById(R.id.gridLayout);

        buttonStart.setVisibility(View.VISIBLE);
    }
}
