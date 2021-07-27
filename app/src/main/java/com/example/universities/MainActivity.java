 package com.example.universities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.universities.Model.DatabaseHelper;
import com.example.universities.Model.UniversityModel;

 public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Declaring variables
    Button btnSaveUni, btnUniList, btnMap, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaveUni =findViewById(R.id.btnSaveUni);
        btnUniList = findViewById(R.id.btnUniList);
        btnMap = findViewById(R.id.btnMap);
        btnExit = findViewById(R.id.btnExit);

        btnSaveUni.setOnClickListener(this);
        btnUniList.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnExit.setOnClickListener(this);

        DatabaseHelper db = new DatabaseHelper(MainActivity.this);
        UniversityModel unimag = new UniversityModel("0000", "UNIVERSIDAD DEL MAGDALENA", "MAGDALENA", "SANTA MARTA", "CARRERA 32 #22 - 08", "11.22151", "-74.1862", "NO DESCRIPCIÓN");
        UniversityModel uniatlantico = new UniversityModel("2214", "UNIVERSIDAD DEL ATLÁNTICO", "ATLÁNTICO", "BARRANQUILLA", "CARRERA 30 #08 - 49", "11.019193", "-74.875389", "NO DESCRIPCIÓN");
        UniversityModel uniantioquia = new UniversityModel("3365", "UNIVERSIDAD DE ANTIOQUIA", "ANTIOQUIA", "MEDELLÍN", "CALLE 67 #53 - 108", "6.26706", "-75.56941", "NO DESCRIPCIÓN");

        db.addUniversity(unimag);
        db.addUniversity(uniatlantico);
        db.addUniversity(uniantioquia);
    }


     @Override
     public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSaveUni:
                Intent i;
                i = new Intent(getApplicationContext(), SaveUniActivity.class);
                startActivity(i);
                break;

            case R.id.btnUniList:
                i = new Intent(getApplicationContext(), UniListActivity.class);
                startActivity(i);
                break;

            case R.id.btnMap:
                i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                break;

            case R.id.btnExit:
                finishAffinity();
                break;
        }
     }

     @Override
     public void onBackPressed(){}
 }