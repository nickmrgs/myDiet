package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    LinearLayout l1;
    LinearLayout dietplan;
    LinearLayout recipes;
    LinearLayout prol;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        l1=(LinearLayout)findViewById(R.id.linearLayoutprof);
        prol=(LinearLayout)findViewById(R.id.pro);
        recipes=(LinearLayout)findViewById(R.id.linearLayoutrecipes);
        dietplan=(LinearLayout)findViewById(R.id.linearLayoutdietplan);


        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfilePage();


            }
        });

      dietplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDietPlanPage();


            }
        });

        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipesPage();


            }
        });

        prol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPro();


            }
        });




    }
    void openRecipesPage()
    {
        Intent intent = new Intent(this,Recipes.class);
        startActivity(intent);

    }

    void openProfilePage()
    {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);
    }
    void openDietPlanPage()
    {
        Intent intent = new Intent(this,dietplan.class);
        startActivity(intent);
    }

    void openPro()
    {
      Intent intent = new Intent(this,PieChart.class);
      startActivity(intent);
    }


}
