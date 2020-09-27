package com.example.mydiet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatDialogFragment;

public class profileDialog extends AppCompatDialogFragment {
    private EditText heightText;
    private EditText weightText;
    private EditText targetText;
    private EditText ageText;
    private Button button1;
    public boolean bmiwrite = false;
    public boolean heightwrite=true;
    public boolean weightwrite=true;
    public boolean agewrite=true;
    public boolean targetwrite=true;
    private profiledialogListener mylistener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater =getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogprofile,null);
        builder.setView(view);
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });






        heightText=view.findViewById(R.id.editText4);
        weightText=view.findViewById(R.id.editText11);
        ageText=view.findViewById(R.id.editText14);
        targetText=view.findViewById(R.id.editText12);
        button1=view.findViewById(R.id.button6);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(heightText.length()==0)
                {
                    heightwrite=false;

                }

                if ((heightText.length()>0)&&(Integer.parseInt(heightText.getText().toString())>250 ||Integer.parseInt(heightText.getText().toString())<100 )){
                    heightwrite=false;
                }
                if(weightText.length()==0)
                {
                    weightwrite=false;
                }
                if((weightText.length()>0)&&(Float.parseFloat(weightText.getText().toString())>300 || Float.parseFloat(weightText.getText().toString())<30))
                {
                    weightwrite=false;
                }

                if(ageText.length()==0)
                {
                    agewrite=false;
                }
                if ((ageText.length()>0)&&(Integer.parseInt(ageText.getText().toString())>100 ||Integer.parseInt(ageText.getText().toString())<0 ))
                {
                    agewrite=false;
                }

                if(targetText.length()==0)
                {
                    targetwrite=false;
                }
                if((targetText.length()>0)&&(Float.parseFloat(targetText.getText().toString())>300 || Float.parseFloat(targetText.getText().toString())<30))
                {
                    targetwrite=false;
                }

                String h = heightText.getText().toString();
                String w = weightText.getText().toString();
                String a = ageText.getText().toString();
                String t=targetText.getText().toString();

                mylistener.applyTexts(h,w,t,a,heightwrite,weightwrite,targetwrite,agewrite);





            }
        });



        //start writing to db






        return builder.create();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mylistener = (profiledialogListener) context;
        } catch (ClassCastException e) {
          throw new ClassCastException(context.toString()+"must implement dialogListener");
        }


    }

    public interface profiledialogListener
    {
        void applyTexts(String height,String weight,String target,String age,Boolean ht,Boolean wt,Boolean tt,Boolean at);
    }




}
