package com.example.autismdiaryapp.ui.Games;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.Games.Game1Reaction.Game1;
import com.example.autismdiaryapp.ui.Games.Game2EmotionRecognition.Game2;
import com.example.autismdiaryapp.ui.Games.Game3MathsQuiz.Game3;
import com.example.autismdiaryapp.ui.Games.Game4Scenarios.Game4;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_games, container, false);





        final Button btn_Game1 = root.findViewById(R.id.launchAct2);
        final Button btn_Game2 = root.findViewById(R.id.launchAct3);
        final Button btn_Game3 = root.findViewById(R.id.launchAct4);
        final Button btn_Game4 = root.findViewById(R.id.launchAct5);


        btn_Game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame1();

            }
        });



        btn_Game2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame2();
            }
        });



        btn_Game3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame3();
            }
        });


        btn_Game4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGame4();
            }
        });

        return root;

    }

    public void openGame1(){
        Intent intent = new Intent(getActivity(), Game1.class);
        startActivity(intent);
    }

    public void openGame2(){
        Intent intent =  new Intent(getActivity(), Game2.class);
        startActivity(intent);
    }

    public void openGame3(){
        Intent intent =  new Intent(getActivity(), Game3.class);
        startActivity(intent);
    }

    public void openGame4(){
        Intent intent =  new Intent(getActivity(), Game4.class);
        startActivity(intent);
    }

}