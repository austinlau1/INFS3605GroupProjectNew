package com.example.infs3605groupprojectnew.Quizzes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.infs3605groupprojectnew.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TestQuiz extends AppCompatActivity {
    private ArrayList<QuizQuestion> quizQuestions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_quiz);

        // Get the list of questions from Firebase Realtime Database
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Easy Quiz");
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

                displayQuestion();
                Button nextButton = findViewById(R.id.next_button);
                nextButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextQuestion();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Log.e("QuizActivity", "Error getting questions from database", databaseError.toException());
            }
        });
    }

    private void displayQuestion() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);

        TextView questionTextView = findViewById(R.id.question_text_view);
        questionTextView.setText(currentQuestion.getQuestion());

        RadioButton option1RadioButton = findViewById(R.id.option1RadioButton);
        option1RadioButton.setText(currentQuestion.getOption1());

        RadioButton option2RadioButton = findViewById(R.id.option2RadioButton);
        option2RadioButton.setText(currentQuestion.getOption2());

        RadioButton option3RadioButton = findViewById(R.id.option3RadioButton);
        option3RadioButton.setText(currentQuestion.getOption3());

        RadioButton option4RadioButton = findViewById(R.id.option4RadioButton);
        option4RadioButton.setText(currentQuestion.getOption4());
    }

    private boolean checkAnswer() {
        QuizQuestion currentQuestion = quizQuestions.get(currentQuestionIndex);
        RadioGroup optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
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
            score++;
        }

        currentQuestionIndex++;

        if (currentQuestionIndex >= quizQuestions.size()) {
            // Quiz is over
            showResult();
        } else {
            displayQuestion();
        }
    }

    private void showResult() {
        String resultMessage = "You scored " + score + " out of " + quizQuestions.size() + "!";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quiz Result")
                .setMessage(resultMessage)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartQuiz();
                    }
                })
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        displayQuestion();
    }

}