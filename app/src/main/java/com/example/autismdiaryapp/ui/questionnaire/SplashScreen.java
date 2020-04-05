package com.example.autismdiaryapp.ui.questionnaire;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.example.autismdiaryapp.MainActivity;
import com.example.autismdiaryapp.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Button buttonStartQuiz = findViewById(R.id.startButton);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuiz();
                closeSplashScreen();
            }
        });

    }
    private void startQuiz(){
        Intent intent  = new Intent(this, runQuestionnaire.class);
        startActivity(intent);

    }
    private void closeSplashScreen(){
        finish();
    }


}
