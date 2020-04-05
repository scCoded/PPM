package com.example.autismdiaryapp.ui.Games.Game4Scenarios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.autismdiaryapp.R;
import com.example.autismdiaryapp.ui.questionnaire.QuizDbHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game4 extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    private TextView textViewScenarioCounter;
    private TextView textViewScenario;
    private Button buttonNext;
    QuizDbHelper dbHelper = new QuizDbHelper(this);

    private int scenarioCounter;
    private int scenarioCountTotal;
    private Scenario currentScenario;

    private List<Scenario> ScenarioList;

    private List<String> ResponsesList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game4);
        textViewScenarioCounter = findViewById(R.id.textViewQuestionCounter);
        textViewScenario = findViewById(R.id.textViewScenario);
        buttonNext = findViewById(R.id.buttonNext);
        recyclerView = findViewById(R.id.recyclerView);

        ScenarioList = dbHelper.getAllScenarios();
        scenarioCountTotal = ScenarioList.size();
        scenarioCounter = 0;
        showNextScenario();

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response r1 = new Response(ResponsesList.get(0), ResponsesList.get(1),ResponsesList.get(2),ResponsesList.get(3));
                dbHelper.addSelectedResponses(r1);
                showNextScenario();

            }
        });


    }

    private void showNextScenario(){
        if (scenarioCounter<scenarioCountTotal)
        {

            textViewScenarioCounter.setText("Scenario: " + (scenarioCounter + 1 ) + " / " + scenarioCountTotal );

            currentScenario = ScenarioList.get(scenarioCounter);
            textViewScenario.setText(currentScenario.getScenario());

            ResponsesList = new ArrayList<>();
            String t1 = currentScenario.getResponse1();
            String t2 = currentScenario.getResponse2();
            String t3 = currentScenario.getResponse3();
            String t4 = currentScenario.getResponse4();

            ResponsesList.add(t1);
            ResponsesList.add(t2);
            ResponsesList.add(t3);
            ResponsesList.add(t4);

            recyclerAdapter = new RecyclerAdapter(ResponsesList);
            recyclerView.setAdapter(recyclerAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(dividerItemDecoration);
            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
            itemTouchHelper.attachToRecyclerView(recyclerView);


            scenarioCounter++;

        }

        else{
            finishGame();
        }

    }



    private void finishGame(){
        finish();
    }




    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(ResponsesList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

}












