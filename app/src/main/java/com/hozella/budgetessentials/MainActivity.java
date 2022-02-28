package com.hozella.budgetessentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hozella.budgetessentials.Fragments.DashboardFragment;
import com.hozella.budgetessentials.Fragments.ExpenseFragment;
import com.hozella.budgetessentials.Fragments.IncomeFragment;
import com.hozella.budgetessentials.Model.Data;
import com.hozella.budgetessentials.Model.Expense;
import com.hozella.budgetessentials.Model.Income;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // Load saved data on application start
        Data.loadData(this);

        /*
        for(Income income : Data.incomeList){
            Toast toast = Toast.makeText(getApplicationContext(), income.getLabel() + " " + income.getAmount() + " " + income.getOccurrence() + " " + income.getNote(), Toast.LENGTH_LONG);
            toast.show();
        }*/

        for(Expense expense : Data.expenseList){
            Toast toast = Toast.makeText(getApplicationContext(), expense.getLabel() + " " + expense.getAmount() + " " + expense.getOccurrence() + " " + expense.getNote(), Toast.LENGTH_LONG);
            toast.show();
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new DashboardFragment()).commit();
        }


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch(item.getItemId()) {
                        case R.id.nav_dashboard:
                            selectedFragment = new DashboardFragment();
                            setTitle("Dashboard");
                            Data.saveData(getApplicationContext());
                            break;
                        case R.id.nav_expense:
                            selectedFragment = new ExpenseFragment();
                            setTitle("Expense");
                            Data.saveData(getApplicationContext());
                            break;
                        case R.id.nav_income:
                            selectedFragment = new IncomeFragment();
                            setTitle("Income");
                            Data.saveData(getApplicationContext());
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

}