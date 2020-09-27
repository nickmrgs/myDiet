package com.example.mydiet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    public String help;
    public static final String TAG = "myDiet.db";
    public static final String TABLE_NAME = "user";
    public static final String COL1 = "username";
    public static final String COL2 = "email";
    public static final String COL3 = "password";

    public static final String TABLE_NAME1 = "info";
    public static final String ICOL1 = "email";
    public static final String ICOL2 = "height";
    public static final String ICOL3 = "weight";
    public static final String ICOL4 = "age";
    public static final String ICOL5 = "sex";
    public static final String ICOL6 = "country";
    public static final String ICOL7 = "bmi";


    public static final String TABLE_NAME2 = "target";
    public static final String TCOL1 = "email";
    public static final String TCOL2="weight";
    public static final String TCOL3="goal";
    public static final String TCOL4="activity";


    public DatabaseHelper(Context context) {
        super(context, TAG, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " TEXT , " + COL2 + "  TEXT PRIMARY KEY, " + " TEXT , " + COL3 + " TEXT );";
        db.execSQL(createTable);

        String createTable1 = "CREATE TABLE " + TABLE_NAME1 + "("
                + ICOL1 + " TEXT PRIMARY KEY, " + ICOL2  + "  TEXT , " + " TEXT , " + ICOL3 + " TEXT , " + ICOL4 + " TEXT , " + ICOL5 + " TEXT , " + ICOL6 + " TEXT , " + ICOL7 + " TEXT , "  + " FOREIGN KEY (" + ICOL1   + ") REFERENCES " + TABLE_NAME + "( " + COL2 + " ) );";
        db.execSQL(createTable1);

        String createTable2 = "CREATE TABLE " + TABLE_NAME2 + "("
                + TCOL1 + " TEXT PRIMARY KEY, " + TCOL2 + "  TEXT , " + "  TEXT , " + TCOL3 + "  TEXT , "  + TCOL4 + " TEXT , " +  "  FOREIGN KEY (" + TCOL1  + ") REFERENCES "  + TABLE_NAME + " ( "  + COL2  + " ) ) ;";
        db.execSQL(createTable2);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean insertData(String mail, String id, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2, mail);
        cv.put(COL1, id);
        cv.put(COL3, password);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;

        }


    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }



    public Cursor getUserInformation(String useremail)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select height,weight,age,country,email,bmi from info where email=?",new String[]{useremail});
        return res;
    }
    public Cursor getCalcInfo(String useremail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select age,sex,bmi from info where email=?",new String[]{useremail});
        return res;
    }

    public Cursor getCalcInfo2(String useremail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select goal,activity from target where email=?",new String[]{useremail});
        return res;

    }

    public Cursor getUserTarget(String useremail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select weight from target where email=?",new String[]{useremail});
        return res;
    }


    public boolean updateHeight(String height,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ICOL2, height);
        long result = db.update(TABLE_NAME1,cv,"email=?",new String[]{mail});
        if (result == -1) {
            return false;
        } else {
            return true;}

    }

    public boolean updateWeight(String weight,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ICOL3, weight);
        long result = db.update(TABLE_NAME1,cv,"email=?",new String[]{mail});
        if (result == -1) {
            return false;
        } else {
            return true;}

    }

    public boolean updateBMI(String bmi,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ICOL7,bmi);
        System.out.println("edw"+bmi);
        long result = db.update(TABLE_NAME1,cv,"email=?",new String[]{mail});
        if (result == -1) {
            return false;
        } else {
            return true;}

    }

    public boolean updateAge(String age,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ICOL4, age);
        long result = db.update(TABLE_NAME1,cv,"email=?",new String[]{mail});
        if (result == -1) {
            return false;
        } else {
            return true;}

    }

    public boolean updateTarget(String target,String mail)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TCOL2, target);
        long result = db.update(TABLE_NAME2,cv,"email=?",new String[]{mail});
        if (result == -1) {
            return false;
        } else {
            return true;}

    }


    public boolean insertTarget(String email,String weight,String goal,String activity)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TCOL1, email);
        cv.put(TCOL2, weight);
        cv.put(TCOL3, goal);
        cv.put(TCOL4, activity);


        long result = db.insert(TABLE_NAME2, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;

        }
    }



    public boolean insertInfo(String email, String height, String weight, String age, String sex, String country,String bmi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ICOL1, email);
        cv.put(ICOL2, height);
        cv.put(ICOL3, weight);
        cv.put(ICOL4, age);
        cv.put(ICOL5, sex);
        cv.put(ICOL6, country);
        cv.put (ICOL7,bmi);


        long result = db.insert(TABLE_NAME1, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;

        }


    }

}
