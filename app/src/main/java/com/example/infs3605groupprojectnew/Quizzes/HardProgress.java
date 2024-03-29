package com.example.infs3605groupprojectnew.Quizzes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infs3605groupprojectnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class HardProgress extends AppCompatActivity {

    private ArrayList<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int correct = 0;
    private int wrong = 0;
    private CountDownTimer timer;
    private AlertDialog dialog;
    RadioGroup optionsRadioGroup;
    RadioButton option1RadioButton;
    RadioButton option2RadioButton;
    RadioButton option3RadioButton;
    RadioButton option4RadioButton;
    TextView questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_template);
        setTitle("Quiz");

        // Get the list of questions from Firebase Realtime Database
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Hard Quiz");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                quizQuestions = new ArrayList<>();
                for (DataSnapshot questionSnapshot : dataSnapshot.getChildren()) {
                    String question = questionSnapshot.child("question").getValue(String.class);
                    String option1 = questionSnapshot.child("option1").getValue(String.class);
                    String option2 = questionSnapshot.child("option2").getValue(String.class);
                    String option3 = questionSnapshot.child("option3").getValue(String.class);
                    String option4 = questionSnapshot.child("option4").getValue(String.class);
                    String answer = questionSnapshot.child("answer").getValue(String.class);

                    // Add the extracted data to an array list
                    QuizQuestion quizQuestion = new QuizQuestion(question, option1, option2, option3, option4, answer);
                    quizQuestions.add(quizQuestion);
                }

                startTimer();

                Button nextButton = findViewById(R.id.next_button);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Check if an option has been selected
                        if (!option1RadioButton.isChecked() && !option2RadioButton.isChecked() &&
                                !option3RadioButton.isChecked() && !option4RadioButton.isChecked()) {
                            Toast.makeText(getApplicationContext(), "Choose an Option", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            nextQuestion();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Log.e("Hard Progress", "Error getting questions from database", databaseError.toException());
            }
        });
    }

    private void startTimer() {
        // Shuffle the quiz questions
        Collections.shuffle(quizQuestions);

        // Select 10 random questions
        quizQuestions = new ArrayList<>(quizQuestions.subList(0, 10));


        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView every second
                int secondsLeft = (int) millisUntilFinished / 1000;
                String timeLeft = String.format(Locale.getDefault(), ":%02d", secondsLeft);
                TextView tvTimer = findViewById(R.id.tv_timer);
                //Change "0" if total time is changed
                tvTimer.setText("0" + timeLeft);
            }

            @Override
            public void onFinish() {
                // Handle timer finish
                showResult();
            }
        }.start();
        displayQuestion();
    }

    private void displayQuestion() {
        questionNumber = findViewById(R.id.question_number);
        int currentQuestionNumber = currentQuestionIndex + 1;
        int totalQuestions = quizQuestions.size();
        String questionNumberText = "Question " + currentQuestionNumber + "/" + totalQuestions;
        questionNumber.setText(questionNumberText);
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

        TextView questionTextView = findViewById(R.id.question_text_view);
        questionTextView.setText(currentQuestion.getQuestion());

        option1RadioButton = findViewById(R.id.option1RadioButton);
        option1RadioButton.setText(currentQuestion.getOption1());

        option2RadioButton = findViewById(R.id.option2RadioButton);
        option2RadioButton.setText(currentQuestion.getOption2());

        option3RadioButton = findViewById(R.id.option3RadioButton);
        option3RadioButton.setText(currentQuestion.getOption3());

        option4RadioButton = findViewById(R.id.option4RadioButton);
        option4RadioButton.setText(currentQuestion.getOption4());
    }

    private boolean checkAnswer() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionId == -1) {
            // No option selected
            return false;
        } else {
            RadioButton selectedRadioButton = findViewById(selectedOptionId);
            String selectedOptionText = selectedRadioButton.getText().toString();
            return selectedOptionText.equals(currentQuestion.getAnswer());
        }
    }

    private void nextQuestion() {
        boolean isCorrect = checkAnswer();

        if (isCorrect) {
            correct++;
            Toast.makeText(HardProgress.this, "Correct", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(HardProgress.this, "Incorrect", Toast.LENGTH_SHORT).show();
        }

        currentQuestionIndex++;

        // Clear the checked radio button
        optionsRadioGroup.clearCheck();

        if (currentQuestionIndex >= quizQuestions.size()) {
            // Quiz is over
            // Stop the timer
            if (timer != null) {
                timer.cancel();
                showResult();
            }
        } else {
            displayQuestion();
        }
    }

    private void showResult() {

        // Inflate the layout file for the "show result" screen
        View resultView = getLayoutInflater().inflate(R.layout.quiz_result, null);

        // Find the FrameLayout view in the layout
        RelativeLayout trophyFrameLayout = resultView.findViewById(R.id.trophyFrameLayout);

        // Create an AlertDialog with the custom layout
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(resultView);
        AlertDialog dialog = builder.create();
        dialog.show();

        // Find any relevant views in the layout and update their values or add listeners
        TextView correctCount = resultView.findViewById(R.id.correctCount);
        correctCount.setText(String.valueOf(correct));
        wrong = 10 - correct;
        TextView incorrectCount = resultView.findViewById(R.id.incorrectCount);
        incorrectCount.setText(String.valueOf(wrong));

        Button restartButton = resultView.findViewById(R.id.restartButton);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                restartQuiz();
            }
        });

        Button exitButton = resultView.findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // If the user scored 100%, show the trophy and confetti animation
        if (correct >= 6) {
            // Set the visibility of the trophy FrameLayout to visible
            trophyFrameLayout.setVisibility(View.VISIBLE);

        }

    }


    private void restartQuiz() {
        correct = 0;
        wrong = 0;
        currentQuestionIndex = 0;
        startTimer();
    }

}