package com.example.autismdiaryapp.ui.Games.Game3MathsQuiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

;

public class Game3 extends AppCompatActivity {

    public String expected;
    public EditText answer;
    public TextView question;
    public TextView score;


    int correct_answer=0;
    int wrong_answer=0;

    private QuizDbHelper dbHelper = new QuizDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game3);
        answer = findViewById(R.id.answer);
        question= findViewById(R.id.question);
        score=findViewById(R.id.score);
        setQuestion();



        Button btn = findViewById(R.id.submit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(answer.getText().toString().equals(expected)){
                    Toast.makeText(getApplicationContext(),"Correct",Toast.LENGTH_SHORT).show();
                    correct_answer++;


                }
                else{
                    Toast.makeText(getApplicationContext(),"Wrong!",Toast.LENGTH_SHORT).show();
                    wrong_answer++;
                }

                answer.setText("");
                setQuestion();

            }
        });
    }





    public void setQuestion() {

        if (correct_answer + wrong_answer > 9) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    score.setText("You answered all 10 questions.");
                    dbHelper.addScore3(correct_answer);
                }
            }, 1500);


            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    finish();
                }
            }, 4500);



        }
        else {
            Random rand = new Random();
            int num_one = ThreadLocalRandom.current().nextInt(0, 10);
            int num_two = ThreadLocalRandom.current().nextInt(0, 10);

            int random_question = rand.nextInt(4);

            if (random_question == 0) {
                question.setText("What is " + num_one + " + " + num_two + " ? ");
                expected = "" + (num_one + num_two);
            } else if (random_question == 1) {
                question.setText("What is " + num_one + " - " + num_two + " ? ");
                expected = "" + (num_one - num_two);
            } else if (random_question == 2) {
                question.setText("What is " + num_one + " / " + num_two + " ? ");
                expected = "" + (num_one / num_two);
            } else if (random_question == 3){
                question.setText("What is " + num_one + " x " + num_two + " ? ");
                expected = "" + (num_one * num_two);
            }
            score.setText("Correct: " + correct_answer + " Incorrect: " + wrong_answer);

        }

    }
}
