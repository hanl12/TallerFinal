package com.example.universities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.universities.Model.DatabaseHelper;
import com.example.universities.Model.UniversityModel;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtName, edtDepartment, edtCity, edtAddress, edtLatitude, edtLongitude, edtDescription;
    Button btnUpdate, btnDelete, btnBack, btnMap;

    int flag = 0, flag1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edtName = findViewById(R.id.edtUName);
        edtDepartment = findViewById(R.id.edtUDepartment);
        edtCity = findViewById(R.id.edtUCity);
        edtAddress = findViewById(R.id.edtUAddress);
        edtLatitude = findViewById(R.id.edtULatitude);
        edtLongitude = findViewById(R.id.edtULongitude);
        edtDescription = findViewById(R.id.edtUDescription);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack1);
        btnBack.setOnClickListener(this);
        btnMap = findViewById(R.id.btnMap);
        btnMap.setOnClickListener(this);

        edtName.setText(UniListActivity.umSelected.getName());
        edtDepartment.setText(UniListActivity.umSelected.getDepartment());
        edtCity.setText(UniListActivity.umSelected.getCity());
        edtAddress.setText(UniListActivity.umSelected.getAddress());
        edtLatitude.setText(UniListActivity.umSelected.getLatitude());
        edtLongitude.setText(UniListActivity.umSelected.getLongitude());
        edtDescription.setText(UniListActivity.umSelected.getDescription());
        edtName.setFocusable(false);
        edtDepartment.setFocusable(false);
        edtCity.setFocusable(false);
        edtAddress.setFocusable(false);
        edtLatitude.setFocusable(false);
        edtLongitude.setFocusable(false);
        edtDescription.setFocusable(false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUpdate:

                if(flag1 == 0){
                    flag1 = 1;
                    edtName.setFocusableInTouchMode(true);
                    edtDepartment.setFocusableInTouchMode(true);
                    edtCity.setFocusableInTouchMode(true);
                    edtAddress.setFocusableInTouchMode(true);
                    edtLatitude.setFocusableInTouchMode(true);
                    edtLongitude.setFocusableInTouchMode(true);
                    edtDescription.setFocusableInTouchMode(true);
                    btnUpdate.setText("ACTUALIZAR");
                }else {
                    updateOnBD();
                }
                break;

            case R.id.btnDelete:
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                if(db.deleteUniversity(UniListActivity.umSelected.getCode())){
                    flag = 1;
                    createAlert("Universidad eliminada");
                }else{
                    createAlert("Error al eliminar universidad");
                }
                break;

            case R.id.btnMap:
                Intent i = new Intent(getApplicationContext(), MapsActivity2.class);
                startActivity(i);
                break;

            case R.id.btnBack1:
                i = new Intent(getApplicationContext(), UniListActivity.class);
                startActivity(i);
                break;
        }
    }

    private void updateOnBD() {
        if(!TextUtils.isEmpty(edtAddress.getText()) &&!TextUtils.isEmpty(edtDescription.getText()) && !TextUtils.isEmpty(edtLongitude.getText()) && !TextUtils.isEmpty(edtLatitude.getText()) && !TextUtils.isEmpty(edtCity.getText()) && !TextUtils.isEmpty(edtName.getText()) && !TextUtils.isEmpty(edtDepartment.getText()))
        {
            UniversityModel um = new UniversityModel(UniListActivity.umSelected.getCode(), edtName.getText().toString(), edtDepartment.getText().toString(), edtCity.getText().toString(), edtAddress.getText().toString(), edtLatitude.getText().toString(), edtLongitude.getText().toString(), edtDescription.getText().toString());
            DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);

            if(db.updateUniversity(um)){
                flag = 1;
                createAlert("Universidad actualizada");
            }
            else{
                createAlert("Error al actualizar");
            }
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