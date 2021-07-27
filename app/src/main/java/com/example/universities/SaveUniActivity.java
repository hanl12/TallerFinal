package com.example.universities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.universities.Model.DatabaseHelper;
import com.example.universities.Model.UniversityModel;

public class SaveUniActivity extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditTexts
    EditText edtCode, edtName, edtDepartment, edtCity, edtAddress, edtLatitude, edtLongitude, edtDescription;

    Button btnSave, btnBack;

    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_uni);

        edtCode = findViewById(R.id.edtCode);
        edtName = findViewById(R.id.edtName);
        edtDepartment = findViewById(R.id.edtDepartment);
        edtCity = findViewById(R.id.edtCity);
        edtAddress = findViewById(R.id.edtAdress);
        edtLatitude = findViewById(R.id.edtLatitude);
        edtLongitude = findViewById(R.id.edtLongitude);
        edtDescription = findViewById(R.id.edtDescription);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack2);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                save();
                break;

            case R.id.btnBack2:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;
        }
    }

    private void save(){
        if(!TextUtils.isEmpty(edtCode.getText()) && !TextUtils.isEmpty(edtAddress.getText()) &&!TextUtils.isEmpty(edtDescription.getText()) && !TextUtils.isEmpty(edtLongitude.getText()) && !TextUtils.isEmpty(edtLatitude.getText()) && !TextUtils.isEmpty(edtCity.getText()) && !TextUtils.isEmpty(edtName.getText()) && !TextUtils.isEmpty(edtDepartment.getText())){
            UniversityModel um = new UniversityModel(edtCode.getText().toString(), edtName.getText().toString(), edtDepartment.getText().toString(), edtCity.getText().toString(), edtAddress.getText().toString(), edtLatitude.getText().toString(), edtLongitude.getText().toString(), edtDescription.getText().toString());

            DatabaseHelper db = new DatabaseHelper(SaveUniActivity.this);

            if(db.addUniversity(um)){
                flag = 1;
                createAlert("Universidad guardada");
            }
            else{
                createAlert("Error al guardar universidad");
            }

        }
        else{
            createAlert("Rellene todos los campos");
        }
    }

    private void createAlert(String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("");
        alert.setMessage(message);
        if(flag == 0){
            alert.setPositiveButton("OK",null);
        }
        else{
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    flag = 0;
                    startActivity(i);
                }
            });
        }
        alert.show();
    }

    @Override
    public void onBackPressed(){}
}