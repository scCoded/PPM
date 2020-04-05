package com.example.autismdiaryapp.ui.Diary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.fragment.app.Fragment;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

public class DiaryEntry extends AppCompatActivity{
    QuizDbHelper myDB;
    TextView dateView, lblHours,lblActivityHours,lblSocialHours;
    EditText editEntry;
    Button btnBack, btnSave,btnUpdate;
    CheckBox achieve1, achieve2, achieve3;
    SeekBar seekActivity,seekSocial,seekSleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        dateView = (TextView) findViewById(R.id.dateView);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        lblHours = (TextView) findViewById(R.id.lblHours);
        lblActivityHours = (TextView) findViewById(R.id.lblActivityHours);
        lblSocialHours = (TextView) findViewById(R.id.lblSocialHours);
        seekSleep = (SeekBar) findViewById(R.id.seekSleep);
        seekSocial = (SeekBar) findViewById(R.id.seekSocial);
        seekActivity = (SeekBar) findViewById(R.id.seekActivity);
        btnSave = (Button) findViewById(R.id.btnSave);
        editEntry = (EditText) findViewById(R.id.editEntry);
        achieve1 = (CheckBox) findViewById(R.id.achieve1);
        achieve2 = (CheckBox) findViewById(R.id.achieve2);
        achieve3 = (CheckBox) findViewById(R.id.achieve3);

        myDB = new QuizDbHelper(this);

        String date = getIntent().getStringExtra("date");

        dateView.setText(date);

        AddData();
        viewData();
        UpdateData();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        seekSleep.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lblHours.setText(String.valueOf(progress));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Stub of method
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Stub of method
            }
        });

        seekSocial.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lblSocialHours.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekActivity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                lblActivityHours.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void AddData() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.addData(dateView.getText().toString(),
                        editEntry.getText().toString(),
                        achieve1.isChecked(),
                        achieve2.isChecked(),
                        achieve3.isChecked(),
                        seekActivity.getProgress(),
                        seekSocial.getProgress(),
                        seekSleep.getProgress());

                if (isInserted = true)
                    Toast.makeText(DiaryEntry.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DiaryEntry.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void viewData() {
        Cursor res = myDB.getData("diary_info", "DATE", dateView.getText().toString());
        if (res.getCount() == 0) {
            // show message
            Toast.makeText(DiaryEntry.this, "Data Not found", Toast.LENGTH_SHORT).show();
            btnSave.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.GONE);
            return;
        }
        Toast.makeText(DiaryEntry.this, "Data Found", Toast.LENGTH_SHORT).show();
        btnUpdate.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
        while (res.moveToNext()) {

            String entry = res.getString(1);
            editEntry.setText(entry);

            Integer goal1 = res.getInt(2);

            if (goal1 == 1) {
                achieve1.setChecked(true);
            } else {
                achieve1.setChecked(false);
            }

            Integer goal2 = res.getInt(3);
            if (goal2 == 1) {
                achieve2.setChecked(true);
            } else {
                achieve2.setChecked(false);
            }

            Integer goal3 = res.getInt(4);
            if (goal3 == 1) {
                achieve3.setChecked(true);
            } else {
                achieve3.setChecked(false);
            }

            Integer activity = res.getInt(5);
            seekActivity.setProgress(activity);
            lblActivityHours.setText(String.valueOf(activity));

            Integer social = res.getInt(6);
            seekSocial.setProgress(social);
            lblSocialHours.setText(String.valueOf(social));

            Integer sleep = res.getInt(7);
            seekSleep.setProgress(sleep);
            lblHours.setText(String.valueOf(sleep));
        }
    }

    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDB.updateData(dateView.getText().toString(),
                                editEntry.getText().toString(),
                                achieve1.isChecked(),
                                achieve2.isChecked(),
                                achieve3.isChecked(),
                                seekActivity.getProgress(),
                                seekSocial.getProgress(),
                                seekSleep.getProgress());
                        if(isUpdate == true)
                            Toast.makeText(DiaryEntry.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DiaryEntry.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}


