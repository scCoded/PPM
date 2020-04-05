package com.example.autismdiaryapp.ui.questionnaire;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;


import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.autismdiaryapp.MainActivity;
import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.questionnaire.Answer;
import com.example.autismdiaryapp.ui.questionnaire.QuizContract;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

import java.util.List;




public class runQuestionnaire extends AppCompatActivity {

    private TextView textViewQuestionCount;
    private TextView textViewQuestion;
    private ProgressBar progressBar;
    private Button buttonNext;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    private QuizDbHelper dbHelper = new QuizDbHelper(this);

    private boolean answered;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        textViewQuestionCount = findViewById(R.id.textViewQuestionCount);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        progressBar = findViewById(R.id.progressBar);
        buttonNext = findViewById(R.id.buttonNext);
        radioGroup = findViewById(R.id.radioGroup);
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);

        questionList = dbHelper.getAllQuestions();
        questionCountTotal = questionList.size();

        showNextQuestion();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!answered) {
                    if (radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(getBaseContext(), "Please select an answer.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }

            }
        });

    }

    private void showNextQuestion() {

        if (radioButton1.isChecked()) {
            Answer a1 = new Answer(radioButton1.getHint().toString());
            dbHelper.addAnswer(a1);
        }
        if (radioButton2.isChecked()) {
            Answer a2 = new Answer(radioButton2.getHint().toString());
            dbHelper.addAnswer(a2);
        }
        if (radioButton3.isChecked()) {
            Answer a3 = new Answer(radioButton3.getHint().toString());
            dbHelper.addAnswer(a3);

        }
        radioGroup.clearCheck();
        currentQuestion = questionList.get(questionCounter);
        if (questionCounter < questionCountTotal) {

            textViewQuestion.setText(currentQuestion.getQuestion());
            radioButton1.setText(currentQuestion.getOption1());
            radioButton2.setText(currentQuestion.getOption2());
            radioButton3.setText(currentQuestion.getOption3());

            questionCounter++;

            textViewQuestionCount.setText("Question: " + questionCounter + " / " + questionCountTotal);
            answered = false;
            buttonNext.setText("Confirm");
            progressBar.setProgress(progressBar.getProgress() + 8);
        } else {
            finishQuiz();


        }
    }

    private void checkAnswer() {
        answered = true;
        if (questionCounter < questionCountTotal) {
            buttonNext.setText("Next");
        } else {
            //SET SharedPreferences "firstStart" to FALSE!!! as quiz has been completed.
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstStart",false);
            editor.apply();

            buttonNext.setText("Finish");
        }

    }

    private void finishQuiz() {
        finish();
    }
}

