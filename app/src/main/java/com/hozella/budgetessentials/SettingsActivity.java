package com.hozella.budgetessentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.hozella.budgetessentials.Model.Data;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);

        Button delExp = findViewById(R.id.del_exp_data);
        Button delInc = findViewById(R.id.del_inc_data);
        Button delAll = findViewById(R.id.del_all_data);

        delExp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Data.deleteData(1);
                Data.saveData(getApplicationContext());
            }
        });

        delInc.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Data.deleteData(2);
                Data.saveData(getApplicationContext());
            }
        });

        delAll.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Data.deleteData(0);
                Data.saveData(getApplicationContext());
            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}