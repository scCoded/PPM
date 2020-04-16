package com.example.autismdiaryapp.ui.Games.Game2EmotionRecognition;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.Games.Game1Reaction.Score1;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2 extends AppCompatActivity {



    Button b_Answer1,b_Answer2,b_Answer3,b_Answer4;

    ImageView iv_emotion;

    List<EmotionItem> list;

    Random r;

    int turn =1;

    int Score;

    private QuizDbHelper dbHelper = new QuizDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game2);

        Score = 0;
        r = new Random();

        iv_emotion= (ImageView) findViewById(R.id.iv_Image);

        b_Answer1 = (Button) findViewById(R.id.b_Answer1);
        b_Answer2 = (Button) findViewById(R.id.b_Answer2);
        b_Answer3 = (Button) findViewById(R.id.b_Answer3);
        b_Answer4 = (Button) findViewById(R.id.b_Answer4);

        list = new ArrayList<>();

        //Add all emotions and names to list.
        for(int i=0; i < new Database().answers.length; i++){
            list.add(new EmotionItem(new Database().answers[i], new Database().emotions[i]));
        }

        //shuffle the countries

        Collections.shuffle(list);

        newQuestion(turn);

        b_Answer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Check if answer is correct
                if(b_Answer1.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {
                    Toast.makeText(Game2.this, "Correct", Toast.LENGTH_SHORT).show();
                    Score +=1;
                    //Check if last Question

                    if(turn < list.size())
                    {
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        Toast.makeText(Game2.this,"You finished the game!",Toast.LENGTH_SHORT).show();
                        dbHelper.addScore2(Score);
                        finish();
                    }

                }
                else{
                    Toast.makeText(Game2.this,"Wrong!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Game2.this,"You lost the game!",Toast.LENGTH_SHORT).show();
                    dbHelper.addScore2(Score);
                    finish();
                }

            }
        });
        b_Answer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(b_Answer2.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {
                    Toast.makeText(Game2.this, "Correct", Toast.LENGTH_SHORT).show();
                    Score +=1;
                    //Check if last Question

                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        Toast.makeText(Game2.this,"You finished the game!",Toast.LENGTH_SHORT).show();
                        dbHelper.addScore2(Score);
                        finish();
                    }
                }
                else{
                    Toast.makeText(Game2.this,"Wrong!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Game2.this,"You lost the game!",Toast.LENGTH_SHORT).show();
                    dbHelper.addScore2(Score);
                    finish();
                }

            }
        });b_Answer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(b_Answer3.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {
                    Toast.makeText(Game2.this, "Correct", Toast.LENGTH_SHORT).show();
                    Score +=1;
                    //Check if last Question

                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        Toast.makeText(Game2.this,"You finished the game!",Toast.LENGTH_SHORT).show();
                        dbHelper.addScore2(Score);

                        finish();
                    }
                }
                else{
                    Toast.makeText(Game2.this,"Wrong!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Game2.this,"You lost the game!",Toast.LENGTH_SHORT).show();
                    dbHelper.addScore2(Score);
                    finish();
                }

            }
        });
        b_Answer4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(b_Answer4.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())) {
                    Toast.makeText(Game2.this, "Correct", Toast.LENGTH_SHORT).show();
                    Score +=1;
                    //Check if last Question

                    if(turn < list.size()){
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        Toast.makeText(Game2.this,"You finished the game!",Toast.LENGTH_SHORT).show();
                        dbHelper.addScore2(Score);
                    }
                }
                else{
                    Toast.makeText(Game2.this,"Wrong!",Toast.LENGTH_SHORT).show();
                    Toast.makeText(Game2.this,"You lost the game!",Toast.LENGTH_SHORT).show();
                    dbHelper.addScore2(Score);

                    finish();
                }

            }
        });

    }

    private void newQuestion(int number){
        //Set emotion image to screen
        iv_emotion.setImageResource(list.get(number-1).getImage());
        //Decide on which button to place correct answer
        int correct_answer = r.nextInt(4)+1;

        int firstButton = number -1;
        int secondButton;
        int thirdButton;
        int forthButton;

        switch(correct_answer){
            case 1:
                b_Answer1.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }
                while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }
                while(thirdButton == firstButton || thirdButton ==secondButton);

                do{
                    forthButton = r.nextInt(list.size());
                }
                while(forthButton == firstButton || forthButton==secondButton || forthButton==thirdButton);


                b_Answer2.setText(list.get(secondButton).getName());
                b_Answer3.setText(list.get(thirdButton).getName());
                b_Answer4.setText(list.get(forthButton).getName());


            case 2:
                b_Answer2.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }
                while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }
                while(thirdButton == firstButton || thirdButton ==secondButton);

                do{
                    forthButton = r.nextInt(list.size());
                }
                while(forthButton == firstButton || forthButton==secondButton || forthButton==thirdButton);


                b_Answer1.setText(list.get(secondButton).getName());
                b_Answer3.setText(list.get(thirdButton).getName());
                b_Answer4.setText(list.get(forthButton).getName());
                break;
            case 3:
                b_Answer3.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }
                while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }
                while(thirdButton == firstButton || thirdButton ==secondButton);

                do{
                    forthButton = r.nextInt(list.size());
                }
                while(forthButton == firstButton || forthButton==secondButton || forthButton==thirdButton);


                b_Answer2.setText(list.get(secondButton).getName());
                b_Answer1.setText(list.get(thirdButton).getName());
                b_Answer4.setText(list.get(forthButton).getName());
                break;
            case 4:
                b_Answer4.setText(list.get(firstButton).getName());

                do{
                    secondButton = r.nextInt(list.size());
                }
                while(secondButton == firstButton);

                do{
                    thirdButton = r.nextInt(list.size());
                }
                while(thirdButton == firstButton || thirdButton ==secondButton);

                do{
                    forthButton = r.nextInt(list.size());
                }
                while(forthButton == firstButton || forthButton==secondButton || forthButton==thirdButton);


                b_Answer2.setText(list.get(secondButton).getName());
                b_Answer3.setText(list.get(thirdButton).getName());
                b_Answer1.setText(list.get(forthButton).getName());
                break;
        }

    }
}
