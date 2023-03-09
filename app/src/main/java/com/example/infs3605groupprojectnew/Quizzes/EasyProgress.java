package com.example.infs3605groupprojectnew.Quizzes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.infs3605groupprojectnew.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class EasyProgress extends AppCompatActivity implements View.OnClickListener {

    // Values for Timer
    private static final long START_TIME_IN_MILLISECONDS = 60000;
    private static TextView timeLeft;
    private static CountDownTimer mCountDownTimer;
    private static Boolean timerRunning;
    private static long timeLeftInMilliseconds = START_TIME_IN_MILLISECONDS;

    // Values for Quiz
    TextView questionNumber;
    TextView totalNumber;
    TextView quizQuestion;
    Button option1;
    Button option2;
    Button option3;
    Button submitButton;
    int correctCount = 0;
    int incorrectCount = 0;
    int totalQuestionCount = 5;
    int currentQuestionCount = 0;
    int randomNumber = 0;
    String selectedAnswer = "";
    ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_progress);

        // Timer
        //timeLeft = findViewById(R.id.timeLeft);

        // Randomiser to get random questions from bank
        int size = 20;

        list = new ArrayList<Integer>(size);
        for(int i = 0; i < size; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
        System.out.println(list);

        //Quiz
        /*questionNumber = findViewById(R.id.questionNumber);
        totalNumber = findViewById(R.id.totalNumber);
        quizQuestion = findViewById(R.id.quizQuestion);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submitButton);*/

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        questionNumber.setText(currentQuestionCount + 1 + "");
        totalNumber.setText("" + totalQuestionCount);

        //resetTimer();
        startTimer();
        loadNewQuestion();
    }

    // Method to Start the Timer
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long timeTillFinish) {
                timeLeftInMilliseconds = timeTillFinish;
                updateCountdownTextView();
            }

            @Override
            public void onFinish() {
                //Add incomplete questions to incorrectCount
                int temp = totalQuestionCount - currentQuestionCount;
                incorrectCount = temp + incorrectCount;
                //Move to CompletedQuiz page
                finishQuiz();
                resetTimer();
            }
        }.start();

        timerRunning = true;

    }

    // Method that updates the timer
    private void updateCountdownTextView() {
        int minutes = (int) timeLeftInMilliseconds / 1000 / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftTimer = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        timeLeft.setText(timeLeftTimer);
    }

    // Method to reset the timer
    private void resetTimer() {
        if (timerRunning = true) {
            mCountDownTimer.cancel();
        }
        timerRunning = false;
        timeLeftInMilliseconds = START_TIME_IN_MILLISECONDS;
        updateCountdownTextView();
    }

    // Code that actions what each button click does
    @Override
    public void onClick(View view) {

        //Drawable btnBackground = getResources().getDrawable(R.drawable.gradient2);
        //Drawable btnBackground2 = getResources().getDrawable(R.drawable.gradient_outline);
        //option1.setBackground(btnBackground);
        //option2.setBackground(btnBackground);
        //option3.setBackground(btnBackground);

        /*Button buttonClicked = (Button) view;
        if (buttonClicked.getId() == R.id.submitButton) {
            if (selectedAnswer.equals(QuizQuestions.savingsAnswers[list.get(randomNumber)])) {
                correctCount++;
            } else {
                incorrectCount++;
            }

            currentQuestionCount++;
            System.out.println("Number is: " + list.get(randomNumber));
            randomNumber++;
            loadNewQuestion();

        } else {

            selectedAnswer = buttonClicked.getText().toString();
            buttonClicked.setBackground(btnBackground2);
        }*/

    }

    // Method to load the next question
    void loadNewQuestion() {

        if (currentQuestionCount == totalQuestionCount) {
            finishQuiz();
            return;
        }
        questionNumber.setText(currentQuestionCount + 1 + "");

        //quizQuestion.setText(QuizQuestions.savingsQuestions[list.get(randomNumber)]);
        //option1.setText(QuizQuestions.savingsOptions[list.get(randomNumber)][0]);
        //option2.setText(QuizQuestions.savingsOptions[list.get(randomNumber)][1]);
        //.setText(QuizQuestions.savingsOptions[list.get(randomNumber)][2]);
    }

    // Method to finish the quiz
    void finishQuiz() {
        Intent intent = new Intent(EasyProgress.this, EasyCompleted.class);
        System.out.println(correctCount);
        System.out.println(incorrectCount);

        //intent.putExtra("ProfileInfo", username);
        intent.putExtra("Correct Score", Integer.toString(correctCount));
        intent.putExtra("Incorrect Score", Integer.toString(incorrectCount));
        startActivity(intent);
        resetTimer();

    }
}