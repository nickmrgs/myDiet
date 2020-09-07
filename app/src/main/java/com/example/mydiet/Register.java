package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

     private Button btn;
     private EditText mailBox;
     private EditText usernameBox;
     private EditText passwordBox;
     DatabaseHelper mydb;
     public String mail;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn=(Button)findViewById(R.id.button);
        mailBox=(EditText)findViewById(R.id.editText);
        usernameBox=(EditText)findViewById(R.id.editText3);
        passwordBox=(EditText)findViewById(R.id.editText2);
        mydb = new DatabaseHelper(this);
        AddData();



    }



    public void AddData()
    {
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mailBox.length() !=0 && usernameBox.length() !=0 && passwordBox.length() !=0)
                {  boolean isInserted = mydb.insertData(mailBox.getText().toString(),usernameBox.getText().toString(),passwordBox.getText().toString());
                if(isInserted)
                {
                    //Msg("Data Inserted");
                    mail=mailBox.getText().toString();
                    openInfoFillActivity();

                }
                else{
                    Msg("Error");
                }


                }
                else
                {
                    Msg("Please fill all the required fields");
                }
            }
        });
    }
//em us pass | pass mail user




    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }

    public void openInfoFillActivity()
    {
        Intent intent = new Intent(this,InfoFill.class);
        intent.putExtra("usermail", mail);
        startActivity(intent);

    }


    }

