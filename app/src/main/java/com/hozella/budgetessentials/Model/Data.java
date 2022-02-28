package com.hozella.budgetessentials.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.hozella.budgetessentials.MainActivity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

    public static ArrayList<Expense> expenseList = new ArrayList<>();
    public static ArrayList<Income> incomeList = new ArrayList<>();
    public static int nextID = 0;

    public static void loadData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String incomeJson = sharedPreferences.getString("incomes", null);
        String expenseJson = sharedPreferences.getString("expenses", null);
        Type incomeType = new TypeToken<ArrayList<Income>>() {}.getType();
        Type expenseType = new TypeToken<ArrayList<Expense>>() {}.getType();
        incomeList = gson.fromJson(incomeJson, incomeType);
        expenseList = gson.fromJson(expenseJson, expenseType);

        if (incomeList == null){
            incomeList = new ArrayList<>();
            System.out.println("No Income Save Data Found!");
        }

        if (expenseList == null){
            expenseList = new ArrayList<>();
            System.out.println("No Expense Save Data Found!");
        }



    }

    public static void saveData(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String incomeJson = gson.toJson(incomeList);
        String expenseJson = gson.toJson(expenseList);
        editor.putString("incomes", incomeJson);
        editor.putString("expenses", expenseJson);
        editor.apply();

        /*
        for(Income income : incomeList){
            System.out.println(income.getLabel() + " " + income.getAmount() + " " + income.getOccurrence() + " " + income.getNote());
        }


        for(Expense expense : expenseList){
            System.out.println(expense.getLabel() + " " + expense.getAmount() + " " + expense.getOccurrence() + " " + expense.getNote());
        }*/
    }

    public static void deleteData(int option) {
        switch (option){
            case 0:
                for (Expense exp: expenseList){
                    expenseList.remove(exp);
                }

                for (Income inc: incomeList){
                    incomeList.remove(inc);
                }
                break;
            case 1:
                for (Expense exp: expenseList){
                    expenseList.remove(exp);
                }
                break;
            case 2:
                for (Income inc: incomeList){
                    incomeList.remove(inc);
                }
                break;
        }
    }

    public static int getNextID(){
        return nextID + 1;
    }

}