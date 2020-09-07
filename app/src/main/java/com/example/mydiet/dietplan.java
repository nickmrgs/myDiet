package com.example.mydiet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class dietplan extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button plus;
    Button minus;
    ImageView cup1;
    ImageView cup2;
    ImageView cup3;
    ImageView cup4;
    ImageView cup5;
    int watercount=0;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView date = (TextView)findViewById(R.id.dateText);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        int printmonth=month+1;

        date.setText(dayOfMonth+"/"+printmonth+"/"+year);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dietplan);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
       TextView date = (TextView)findViewById(R.id.dateText);
       plus = (Button)findViewById(R.id.plusButton);
       minus = (Button)findViewById(R.id.minusButton);
       cup1= (ImageView) findViewById(R.id.watercupView);
       cup2= (ImageView) findViewById(R.id.watercupView2);
       cup3=(ImageView)findViewById(R.id.watercupView3) ;
       cup4=(ImageView)findViewById(R.id.watercupView4);
       cup5=(ImageView)findViewById(R.id.watercupView5);



        cup1.setVisibility(View.INVISIBLE);
        cup2.setVisibility(View.INVISIBLE);
        cup3.setVisibility(View.INVISIBLE);
        cup4.setVisibility(View.INVISIBLE);
        cup5.setVisibility(View.INVISIBLE);




        Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int day=c.get(Calendar.DAY_OF_MONTH);
        int printmonth=month+1;

        date.setText(day+"/"+printmonth+"/"+year);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");








            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(watercount<5)
                {
                    watercount=watercount+1;
                }

                setWaterCups();



            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(watercount>0){
                    watercount=watercount-1;
                }

                setWaterCups();
            }
        });












    }

    private void setWaterCups()
    {
        if(watercount==0)
        {
            cup1.setVisibility(View.INVISIBLE);
            cup2.setVisibility(View.INVISIBLE);
            cup3.setVisibility(View.INVISIBLE);
            cup4.setVisibility(View.INVISIBLE);
            cup5.setVisibility(View.INVISIBLE);
        }
        else if(watercount==1)
        {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.INVISIBLE);
            cup3.setVisibility(View.INVISIBLE);
            cup4.setVisibility(View.INVISIBLE);
            cup5.setVisibility(View.INVISIBLE);

        }
        else if(watercount==2)
        {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.VISIBLE);
            cup3.setVisibility(View.INVISIBLE);
            cup4.setVisibility(View.INVISIBLE);
            cup5.setVisibility(View.INVISIBLE);

        }
        else if(watercount==3)
        {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.VISIBLE);
            cup3.setVisibility(View.VISIBLE);
            cup4.setVisibility(View.INVISIBLE);
            cup5.setVisibility(View.INVISIBLE);

        }
        else if(watercount==4)
        {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.VISIBLE);
            cup3.setVisibility(View.VISIBLE);
            cup4.setVisibility(View.VISIBLE);
            cup5.setVisibility(View.INVISIBLE);

        }
        else if(watercount==5)
        {
            cup1.setVisibility(View.VISIBLE);
            cup2.setVisibility(View.VISIBLE);
            cup3.setVisibility(View.VISIBLE);
            cup4.setVisibility(View.VISIBLE);
            cup5.setVisibility(View.VISIBLE);

        }



    }



}
