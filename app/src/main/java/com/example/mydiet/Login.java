package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Struct;

public class Login extends AppCompatActivity {
    private Button button;
    private EditText emailBox,passBox;
    DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailBox=(EditText)findViewById(R.id.editText);
        passBox=(EditText)findViewById(R.id.editText2);
        mydb = new DatabaseHelper(this);
        button=(Button)findViewById(R.id.button);
        GetData();


    }
public void GetData()
{
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Cursor res = mydb.getAllData();
            if(res.getCount() == 0)
            {
                // show message
                Msg("Nothing found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {

                String mail = res.getString(1);
                String pass= res.getString(3);
                String k =emailBox.getText().toString();

                if(emailBox.length() !=0 && passBox.length() !=0)
                {
                    if(k.equals(mail))
                    {
                        if(pass.equals(passBox.getText().toString()))
                        {
                            openLoginActivity();
                            break;
                        }
                        else
                        {
                            Msg("Incorrect password for" +" "+ mail);
                            break;
                        }
                    }
                    else
                    {
                        Msg("email was not found");
                        break;

                    }

                }
                else
                    {
                        Msg("Please fill in the fields");
                    }


            }

        }
    });
}
    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }

    public void openLoginActivity()
    {
        Intent intent = new Intent(this,MainMenu.class);
        startActivity(intent);

    }
}
