package com.example.autismdiaryapp.ui.Diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GalleryFragment extends Fragment {

    private HomeViewModel homeViewModel;

    QuizDbHelper myDB;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);



        final CalendarView calendarView = root.findViewById(R.id.calendarView);
        final TextView dateView = root.findViewById(R.id.textView);
        myDB = new QuizDbHelper(getActivity());
        SimpleDateFormat SDF = new SimpleDateFormat("dd/M/yyyy");
        String date = SDF.format(new Date(calendarView.getDate()));

        dateView.setText(date);

        final Button openButton = root.findViewById(R.id.openButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (dayOfMonth + "/" + (month + 1) + "/" + year);
                dateView.setText(date);
            }
        });


        openButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DiaryEntry.class);
                intent.putExtra("date", dateView.getText());
                startActivity(intent);
            }
        });


        return root;
    }
}

