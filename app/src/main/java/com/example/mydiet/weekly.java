package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class weekly extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly);
        TextView breakfast = (TextView)findViewById(R.id.breakfast);
        TextView breakfastcalories=(TextView)findViewById(R.id.breakfastcalories);
        TextView snacks = (TextView)findViewById(R.id.snacks);
        TextView snackscalories=(TextView)findViewById(R.id.snackcalories);
        TextView lunch= (TextView)findViewById(R.id.lunch);
        TextView lunchCalories=(TextView)findViewById(R.id.lunchCalories);
        TextView dinner = (TextView)findViewById(R.id.dinner);
        TextView dinnerCalories=(TextView)findViewById(R.id.dinnerCalories);

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        Random r = new Random();
        int n1 =r.nextInt(15);
        List<String> breakfastlist = databaseAccess.getBreakfast(String.valueOf(n1));
        breakfast.setText(breakfastlist.get(0).toString());
        breakfastcalories.setText(breakfastlist.get(1).toString());
        databaseAccess.close();
        databaseAccess.open();
        int k =2;
        List<String> snackslist = databaseAccess.getSnacks(String.valueOf(k));
        snacks.setText(snackslist.get(0).toString());
        snackscalories.setText(snackslist.get(1).toString());
        databaseAccess.close();
        int k1=3;
        databaseAccess.open();
        List<String> lunchlist = databaseAccess.getLunch(String.valueOf(k));
        lunch.setText(lunchlist.get(0).toString());
        lunchCalories.setText(lunchlist.get(1).toString());
        databaseAccess.close();

        int k2=3;
        databaseAccess.open();
        List<String> dinnerList = databaseAccess.getLunch(String.valueOf(k2));
        dinner.setText(dinnerList.get(0).toString());
        dinnerCalories.setText(dinnerList.get(1).toString());
        databaseAccess.close();








    }

    private void selectbreakfast()
    {

    }
}
