package com.example.autismdiaryapp.ui.Games.Game1Reaction;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.autismdiaryapp.R;

public class Game1 extends AppCompatActivity {


    TextView txt_Best;
    Button btn_Main,btn_Start;
    long startTime, endTime, currentTime, bestTime = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        btn_Start = (Button) findViewById(R.id.btn_Start);
        btn_Main = (Button) findViewById(R.id.btn_Main);
        txt_Best = (TextView) findViewById(R.id.txt_Best);

        txt_Best.setText("Best: " + bestTime + " ms");
        btn_Main.setText("Press 'Start' to begin");
        btn_Start.setEnabled(true);
        btn_Main.setEnabled(false);


        btn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Start.setEnabled(false);
                btn_Main.setText("WAIT");

                Handler handler = new Handler();
                handler.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        startTime = System.currentTimeMillis();
                        btn_Main.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
                        btn_Main.setText("PRESS");
                        btn_Main.setEnabled(true);
                    }
                },2000);
            }
        });

        btn_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endTime = System.currentTimeMillis();
                currentTime = endTime - startTime;
                btn_Main.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
                btn_Main.setText(currentTime + "ms");
                btn_Start.setEnabled(true);
                btn_Main.setEnabled(false);

                if(currentTime < bestTime){
                    bestTime = currentTime;
                    txt_Best.setText("Best: " + bestTime + " ms");
                }
            }
        });
    }
}