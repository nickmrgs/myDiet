package com.example.mydiet;
import com.example.mydiet.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;

public class InfoFill extends AppCompatActivity {
    public int weight;
    public  int height;
    public  double bmi;
    private Button button;
    private EditText heightBox;
    private EditText weightBox;
    private EditText ageBox;
    private boolean correctdata;
    DatabaseHelper mydb;
    Register myreg = new Register();
    public String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_fill);
        Intent intent= getIntent(); // gets the previously created intent
        final String mail = intent.getStringExtra("usermail");
        email=mail;
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        heightBox=(EditText)findViewById(R.id.editText8);
        weightBox=(EditText)findViewById(R.id.editText7);
        ageBox=(EditText)findViewById(R.id.editText9);
        button=(Button)findViewById(R.id.button4);
        mydb = new DatabaseHelper(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                correctdata=true;

                if(heightBox.length() !=0 && weightBox.length() !=0 && ageBox.length() !=0)
                {
                  height =  Integer.parseInt(heightBox.getText().toString());
                  weight =  Integer.parseInt(weightBox.getText().toString());
                  int age  = Integer.parseInt(ageBox.getText().toString());
                  bmi = ((float)weight/(height * height))*10000;
                    bmi = Math.round(bmi);
                  Msg(Double.toString(bmi));
                  if (height<100 || height > 250)
                  {
                      correctdata=false;
                  }
                  if(weight<30 || weight >300)
                  {
                      correctdata=false;
                  }
                  if(age<0 || age>100)
                  {
                      correctdata=false;
                  }
                  if(correctdata)
                  {
                      boolean isInserted = mydb.insertInfo(mail,heightBox.getText().toString(),weightBox.getText().toString(),ageBox.getText().toString(),spinner.getSelectedItem().toString(),spinner2.getSelectedItem().toString(),Double.toString(bmi));
                      if(isInserted)
                      {
                          openFill2Activity();

                      }
                      else
                      {
                          Msg("Error");
                      }

                  }
                  else
                  {
                      Msg("Wrong Data");

                  }

                }
                else
                {
                    Msg("Fill all the fields");
                }




            }
        });



        //Spinners
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Sex,R.layout.simple_spinner );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Country,R.layout.simple_spinner );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);


        //TexParse
















    }

    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }


    public void openFill2Activity()
    {
        Intent intent = new Intent(this,fillinfo2.class);
        intent.putExtra("usermail", email);
        startActivity(intent);

    }




}
