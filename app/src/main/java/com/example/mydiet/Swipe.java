package com.example.mydiet;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Swipe extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.dveg, "Vegeterian", "placeholder"));
        models.add(new Model(R.drawable.dpro, "Protein", "placeholder"));
        models.add(new Model(R.drawable.dcla, "Poster", "placeholder"));
        models.add(new Model(R.drawable.dsca, "Namecard", "placeholder"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.brocolli),
                getResources().getColor(R.color.protein),
                getResources().getColor(R.color.classic),
                getResources().getColor(R.color.purple2),



        };

        colors = colors_temp;

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position <(adapter.getCount() -1) && position <(colors.length - 1)) {
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position + 1]
                            )
                    );
                }

                else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }



            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



    }
}
