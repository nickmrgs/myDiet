package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Profile extends AppCompatActivity implements profileDialog.profiledialogListener {
    DatabaseHelper mydb;
    TextView tv0;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    Register myreg = new Register();
    String mail;
    String height;
    String weight;
    String age;
    String country;
    String target;
    String email;
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tv0=(TextView) findViewById(R.id.textView35);
        tv1=(TextView) findViewById(R.id.textView36);
        tv2=(TextView) findViewById(R.id.textView37);
        tv3=(TextView) findViewById(R.id.textView38);
        tv4=(TextView) findViewById(R.id.textView40);
        tv5=(TextView) findViewById(R.id.textView41);
        tv6=(TextView)findViewById(R.id.textView35); //email
        btn1=(Button)findViewById(R.id.button5);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();


            }
        });



        ImageView imageView = findViewById(R.id.imageView4);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.avatar);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
        roundedBitmapDrawable.setCircular(true);
        imageView.setImageDrawable(roundedBitmapDrawable);
        mydb = new DatabaseHelper(this);
        Cursor res = mydb.getUserInformation("p");
        Cursor res1 =mydb.getUserTarget("p");

        if(res.getCount()==0)
        {
            Msg("Nothing found");
            return;
        }
        while (res.moveToNext()) {

           height = res.getString(0);
           weight = res.getString(1);
           age    = res.getString(2);
           country =res.getString(3);
           email =  res.getString(4);




        }

        tv1.setText(height);
        tv2.setText(weight);
        tv5.setText(age);
        tv3.setText(country);
        tv6.setText(email);

        if(res1.getCount()==0)
        {
            Msg("Nothing found");
            return;

        }

        while (res1.moveToNext()) {

            target = res1.getString(0);



        }

        tv4.setText(target);




    }
    public void OpenDialog()
    {
        profileDialog myprof = new profileDialog();
        myprof.show(getSupportFragmentManager(),"example dialog");



    }

    @Override
    public void applyTexts(String height,String weight,String target,String age,Boolean ht,Boolean wt,Boolean tt,Boolean at)
    {
        if(ht)
        {
            tv1.setText(height);
            boolean heightInsert = mydb.updateHeight(height,tv0.getText().toString());
            if(!heightInsert)
            {
                Msg("Error Updating Height Value");
            }

        }
        if(wt)
        {
            tv2.setText(weight);
            boolean weightInsert = mydb.updateWeight(weight,tv0.getText().toString());
            if(!weightInsert)
            {
                Msg("Error Updating Weight Value");
            }
        }
        ////update bmi
        if(ht||wt)
        {
            String he = tv1.getText().toString();
            String we = tv2.getText().toString();
            double h =Double.parseDouble(he);
            double w =Double.parseDouble(we);
            double bmi = ((float)w/(h * h))*10000;
            bmi = Math.round(bmi);
            String bmis =String.valueOf(bmi);
            boolean bmiInsert = mydb.updateBMI(bmis,tv0.getText().toString());
            if(!bmiInsert)
            {
                Msg("Error Updating BMI Value");
            }
        }


        if(at)
        {
            tv5.setText(age);
            boolean ageInsert = mydb.updateAge(age,tv0.getText().toString());
            if(!ageInsert)
            {
                Msg("Error Updating age Value");
            }

        }
        if(tt)
        {
            tv4.setText(target);
            boolean targetInsert = mydb.updateTarget(target,tv0.getText().toString());
            if(!targetInsert)
            {
                Msg("Error Updating Target Weight Value");
            }
        }




    }

    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }
}
