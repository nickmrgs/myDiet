package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razerdp.widget.animatedpieview.AnimatedPieView;
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig;
import com.razerdp.widget.animatedpieview.data.SimplePieInfo;

public class PieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piechart);

        drawPie();

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
}