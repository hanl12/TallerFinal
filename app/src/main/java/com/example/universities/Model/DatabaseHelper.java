package com.example.universities.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.Nullable;

import com.example.universities.R;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String dbName = "mUniversities.db";

    public static Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE IF NOT EXISTS " + "UNIVERSITIES" +
                " ( " + "CODE" + " TEXT PRIMARY KEY, " + "NAME" +
                " TEXT, " + "DEPARTMENT" + " TEXT, " + "CITY" + " TEXT, " +
                "ADDRESS" + " TEXT, " + "LATITUDE" + " TEXT, " + "LONGITUDE" + " TEXT, " +
                "DESCRIPTION" + " TEXT" + ")";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public boolean addUniversity(UniversityModel im){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("CODE", im.getCode());
        cv.put("NAME", im.getName());
        cv.put("DEPARTMENT", im.getDepartment());
        cv.put("CITY", im.getCity());
        cv.put("ADDRESS", im.getAddress());
        cv.put("LATITUDE", im.getLatitude());
        cv.put("LONGITUDE", im.getLongitude());
        cv.put("DESCRIPTION", im.getDescription());

        long insert = db.insert("UNIVERSITIES", null, cv);
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public SimpleCursorAdapter populateLisView(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT CODE AS _id, " + "NAME" + ", " +
                "DEPARTMENT" + ", " + "CITY" + ", " + "ADDRESS" + ", " +
                "LATITUDE" + ", " + "LONGITUDE" + ", " + "DESCRIPTION" + " FROM UNIVERSITIES";

        Cursor cursor = db.rawQuery(query, null);

        String[] fields = new String[]{
                "_id", "NAME", "DEPARTMENT", "CITY", "ADDRESS", "LATITUDE", "LONGITUDE", "DESCRIPTION"
        };

        int[] ids = new int[]{
                R.id.tvCode, R.id.tvName, R.id.tvDepartment, R.id.tvCity, R.id.tvAddress, R.id.tvLatitude, R.id.tvLongitude, R.id.tvDescription
        };
        SimpleCursorAdapter sCursor = new SimpleCursorAdapter(
          context,
          R.layout.single_item,
          cursor,
          fields,
          ids,
          0
        );

        return sCursor;
    }

    public boolean updateUniversity(UniversityModel im){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("NAME", im.getName());
        cv.put("DEPARTMENT", im.getDepartment());
        cv.put("CITY", im.getCity());
        cv.put("ADDRESS", im.getAddress());
        cv.put("LATITUDE", im.getLatitude());
        cv.put("LONGITUDE", im.getLongitude());
        cv.put("DESCRIPTION", im.getDescription());

        int rows = db.update("UNIVERSITIES", cv, "CODE" + "=" + im.getCode(), null);

        if(rows <= 0){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT NAME, LATITUDE, LONGITUDE FROM UNIVERSITIES";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }


    public boolean deleteUniversity(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete("UNIVERSITIES", "CODE" + "=" + id, null);
        if(rows <= 0){
            return false;
        }
        else{
            return true;
        }
    }

}
