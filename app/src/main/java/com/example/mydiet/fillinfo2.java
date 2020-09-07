package com.example.mydiet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class fillinfo2 extends AppCompatActivity {
    private Button button;
    private EditText editText;
    InfoFill myinfofill = new InfoFill();
    DatabaseHelper mydb;
    Register myreg = new Register();
    public String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillinfo2);
        mydb = new DatabaseHelper(this);
        Intent intent= getIntent(); // gets the previously created intent
        final String mail = intent.getStringExtra("usermail");
        email=mail;
        final Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.level,R.layout.simple_spinner );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.goal,R.layout.simple_spinner );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter1);

        button=(Button)findViewById(R.id.button4);
        editText=(EditText)findViewById(R.id.editText7);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean truedata = true;

                if(editText.length()!=0)
                {
                    int target = Integer.parseInt(editText.getText().toString());
                    if(target>200 || target <30)
                    {
                        Msg("False Data");
                    }
                    else
                    {
                        if(target>myinfofill.weight && spinner.getSelectedItem()=="Lose Weight")
                        {
                            Msg("False Data");
                        }
                        else
                        {
                            boolean isInserted = mydb.insertTarget(email,editText.getText().toString(),spinner.getSelectedItem().toString(),spinner2.getSelectedItem().toString());
                            if(isInserted)
                            {
                                openfillProfilePicActivity();
                            }
                            else
                            {
                                Msg("Error");
                            }
                        }

                    }

                }


            }
        });



    }

    private void Msg(String mess)
    {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT).show();
    }

    public void openfillProfilePicActivity()
    {
        Intent intent = new Intent(this,Profile.class);
        startActivity(intent);

    }
}
