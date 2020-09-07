package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainMenu extends AppCompatActivity {
    LinearLayout l1;
    LinearLayout dietplan;
    LinearLayout recipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        l1=(LinearLayout)findViewById(R.id.linearLayoutprof);
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


}
