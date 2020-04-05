package com.example.autismdiaryapp.ui.Graphs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.autismdiaryapp.R;


import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class StatisticsFragment extends Fragment {

    private StatisticsViewModel StatisticsViewModel;


    long date = System.currentTimeMillis();
    private BarChart barChart;
    private Button button;
    private EditText editText;
    private database db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatisticsViewModel =
                ViewModelProviders.of(this).get(StatisticsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);

         barChart = (BarChart) root.findViewById(R.id.barchart);
         button = (Button) root.findViewById(R.id.button);
         editText = (EditText) root.findViewById(R.id.editText);

        addDataToGraph();
        barChart.invalidate();

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                SaveToDatabase();
            }
        });

        return root;

    }

    public void SaveToDatabase(){
        db = new database(getActivity());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMM");
        String xvalue = sdf.format(date);
        String yvalue = editText.getText().toString();
        db.saveData(xvalue, yvalue);
        addDataToGraph();
        barChart.invalidate();
        db.close();
    }

    public void addDataToGraph(){
        db = new database(getActivity());

        final ArrayList<BarEntry> yVals = new ArrayList<BarEntry>();
        final ArrayList<String> ydata = db.queryYData();

        for(int i = 0; i < db.queryYData().size(); i++){
            BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(db.queryYData().get(i)));
            yVals.add(newBarEntry);
        }

        final ArrayList<String> xVals = new ArrayList<String>();
        final ArrayList<String> xdata = db.queryXData();

        for(int i = 0; i < db.queryXData().size(); i++){
            xVals.add(xdata.get(i));
        }

        BarDataSet dataSet = new BarDataSet(yVals, "Example Graph");

        ArrayList<IBarDataSet> dataSets1 = new ArrayList<>();
        dataSets1.add(dataSet);

        BarData data = new BarData(dataSets1);

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xVals));

        barChart.setData(data);

        XAxis xAxis = barChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setDrawLabels(true);
        xAxis.isCenterAxisLabelsEnabled();
        xAxis.setGranularityEnabled(true);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);

        barChart.setMaxVisibleValueCount(5);
        barChart.setFitBars(true);

    }
}