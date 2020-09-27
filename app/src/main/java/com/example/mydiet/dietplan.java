package com.example.mydiet;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.database.Cursor;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;


public class dietplan extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button plus;
    Button minus;
    ImageView cup1;
    ImageView cup2;
    ImageView cup3;
    ImageView cup4;
    ImageView cup5;
    TextView calo;
    String age;
    String sex;
    String bmi;
    String goal;
    String activity;
    DatabaseHelper mydb;
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
        calo = (TextView)findViewById(R.id.caloriesView);
       TextView date = (TextView)findViewById(R.id.dateText);
       plus = (Button)findViewById(R.id.plusButton);
       minus = (Button)findViewById(R.id.minusButton);
       cup1= (ImageView) findViewById(R.id.watercupView);
       cup2= (ImageView) findViewById(R.id.watercupView2);
       cup3=(ImageView)findViewById(R.id.watercupView3) ;
       cup4=(ImageView)findViewById(R.id.watercupView4);
       cup5=(ImageView)findViewById(R.id.watercupView5);
       database();




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

    public void database()
    {

        mydb = new DatabaseHelper(this);
        Cursor res = mydb.getCalcInfo("p");
        Cursor res2 = mydb.getCalcInfo2("p");

        if(res.getCount()==0)
        {
            Msg("Nothing found");
            return;

        }
        while (res.moveToNext()) {

            age    = res.getString(0);
            sex =  res.getString(1);
            bmi =res.getString(2);
        }
        if(res.getCount()==0)
        {
            Msg("Nothing found");
            return;

        }if(res2.getCount()==0)
    {
        Msg("Nothing found");
        return;

    }
        while (res2.moveToNext()) {

            goal  = res2.getString(0);
            activity=  res2.getString(1);
        }

        calo.setText(String.valueOf(calculateDailyCalories(age,sex,bmi,goal,activity)));




    }

    private int calculateDailyCalories(String age,String sex,String bmi,String goal,String activity)
    {
        int dailycalories=0;
        ///exw mperdepsei to goal me to activity sthn vasi

        int myage = Integer.parseInt(age);
        ///ANTRES///////////////
        if(sex.equals("Male"))
        {

            if(myage<9)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1200;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 1600;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2000;
                }


            }
            if(myage>9&& myage<=14)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1800;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 2000;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2200;
                }


            }

            if(myage>14&& myage<=18)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=2400;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 2800;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 3000;
                }


            }
           if(myage>18&& myage<=30)
           {
               if(goal.equals("Low"))
               {
                   dailycalories=2400;
               }
               if(goal.equals("Medium"))
               {
                   dailycalories= 2800;
               }
               if(goal.equals("High"))
               {
                   dailycalories= 3000;
               }


           }
           else if(myage>30 && myage<=50)
           {

               if(goal.equals("Low"))
               {
                   dailycalories=2200;
               }
               if(goal.equals("Medium"))
               {
                   dailycalories= 2600;
               }
               if(goal.equals("High"))
               {
                   dailycalories= 2800;
               }


           }
           else if(myage>50)
           {

               if(goal.equals("Low"))
               {
                   dailycalories=2000;
               }
               if(goal.equals("Medium"))
               {
                   dailycalories= 2400;
               }
               if(goal.equals("High"))
               {
                   dailycalories= 2600;
               }

           }
        }


        //// GYNAIKESS
        else if (sex.equals("Female"))
        {
            if(myage<9)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1200;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 1400;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 1400;
                }


            }
            if(myage>9&& myage<=14)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1600;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 1600;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2000;
                }


            }

            if(myage>9&& myage<=14)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1600;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 1600;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2000;
                }


            }
            if(myage>14&& myage<=18)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=1800;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 2000;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2400;
                }


            }
            if(myage>18&& myage<=30)
            {
                if(goal.equals("Low"))
                {
                    dailycalories=2000;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 2200;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2400;
                }


            }
            else if(myage>30 && myage<=50)
            {

                if(goal.equals("Low"))
                {
                    dailycalories=1800;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 2000;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2200;
                }


            }
            else if(myage>50)
            {

                if(goal.equals("Low"))
                {
                    dailycalories=1600;
                }
                if(goal.equals("Medium"))
                {
                    dailycalories= 1800;
                }
                if(goal.equals("High"))
                {
                    dailycalories= 2000;
                }

            }

        }
        if(activity.equals("Lose Weight"))
        {
            dailycalories=dailycalories-500;
        }
        else if(activity.equals("Gain Weight"))
        {
            dailycalories=dailycalories+500;
        }
        return dailycalories;

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
    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }






}
