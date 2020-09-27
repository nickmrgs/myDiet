package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends AppCompatActivity {

BarChart barChart;
BarData barData;
BarDataSet barDataSet;
ArrayList barEntries;
DatabaseHelper mydb;
TextView t1;
TextView t2;
TextView t3;
String height;
String weight;
String bmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);
        barChart=findViewById(R.id.barchart);
        t1=(TextView)findViewById(R.id.bmi);
        t2=(TextView)findViewById(R.id.weight);
        t3=(TextView)findViewById(R.id.height);


        drawPie();
        getEntries();

        barDataSet = new BarDataSet(barEntries, "Data Set");
        barData = new BarData(barDataSet);

        barChart.setData(barData);

        barDataSet.setColors(new int[]{Color.parseColor("#ae87d0")});
        barDataSet.setValueTextColor(android.R.color.black);
        barDataSet.setValueTextSize(16f);
        dbhelper();

    }
    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }

    public void dbhelper()
    {
        mydb = new DatabaseHelper(this);
        Cursor res = mydb.getUserInformation("p");

        if(res.getCount()==0)
        {
            Msg("Nothing found");
            return;
        }
        while (res.moveToNext()) {

            height = res.getString(0);
            weight = res.getString(1);
            bmi = res.getString(5);
            Msg(bmi);





        }
        t1.setText("   BMI   \n"+bmi);
        t2.setText(" Weight\n"+weight);
        t3.setText(" Height\n"+height);

    }


    public void drawPie()

    {
        AnimatedPieView mAnimatedPieView = findViewById(R.id.Pie);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.startAngle(-90)// Starting angle offset
                .addData(new SimplePieInfo(30, Color.parseColor("#ae87d0"), "Breakfast"))//Data (bean that implements the IPieInfo interface)
                .addData(new SimplePieInfo(18.0f,Color.parseColor("#7851a9"), "Snack")).drawText(true).strokeMode(false)
                .addData(new SimplePieInfo(40, Color.parseColor("#522d80"),"Lunch"))
                .addData(new SimplePieInfo(12,Color.parseColor("#330066"),"Dinner"))
      .duration(2000).textSize(53);// draw pie animation duration

// The following two sentences can be replace directly 'mAnimatedPieView.start (config); '
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();
    }


    private void getEntries(){

        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1f,2));
        barEntries.add(new BarEntry(2f,4));
        barEntries.add(new BarEntry(3f,6));
        barEntries.add(new BarEntry(4f,1));
        barEntries.add(new BarEntry(5f,5));
        barEntries.add(new BarEntry(6f,3));



    }





}