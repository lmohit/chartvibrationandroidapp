package com.cambotutorial.sovary.chartjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList barArraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BarChart barChart = findViewById(R.id.barchart);
        BarEntry barEntry = new BarEntry(2f, 10);
        getData(barEntry);
        BarDataSet barDataSet = new BarDataSet(barArraylist, "Cambo Tutorial");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        //color bar data set
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        //text color
        barDataSet.setValueTextColor(Color.BLACK);
        //settting text size
        barDataSet.setValueTextSize(16f);

        barChart.getDescription().setEnabled(true);
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                int x = barChart.getBarData().getDataSetForEntry(e).getEntryIndex((BarEntry) e);

                BarEntry entry = (BarEntry) barArraylist.get(x);
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    v.vibrate(VibrationEffect.createOneShot(500 * (int) entry.getY(), VibrationEffect.DEFAULT_AMPLITUDE));
                } else {
                    //deprecated in API 26
                    v.vibrate(500);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    private void getData(BarEntry barEntry) {
        barArraylist = new ArrayList();
        barArraylist.add(barEntry);
        barArraylist.add(new BarEntry(3f, 1));
        barArraylist.add(new BarEntry(4f, 5));
        barArraylist.add(new BarEntry(5f, 10));
        barArraylist.add(new BarEntry(6f, 15));

    }


}