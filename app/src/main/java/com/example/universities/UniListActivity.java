package com.example.universities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.universities.Model.DatabaseHelper;
import com.example.universities.Model.UniversityModel;

public class UniListActivity extends AppCompatActivity {

    ListView lv;
    TextView tvName, tvCode, tvDepartment, tvCity, tvAddress, tvDescription;
    public DatabaseHelper db;
    public static UniversityModel umSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uni_list);

        tvName = findViewById(R.id.tvName);
        tvCode = findViewById(R.id.tvCode);
        tvDepartment = findViewById(R.id.tvDepartment);
        tvCity = findViewById(R.id.tvCity);
        tvAddress = findViewById(R.id.tvAddress);
        tvDescription = findViewById(R.id.tvDescription);
        lv = findViewById(R.id.lvList);

        db = new DatabaseHelper(UniListActivity.this);
        SimpleCursorAdapter spa = db.populateLisView();
        lv.setAdapter(spa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) spa.getItem(position);
                umSelected = new UniversityModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                Intent i = new Intent(getApplicationContext(), UpdateActivity.class);
                startActivity(i);
                
            }
        }

        );
    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

}