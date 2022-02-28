package com.hozella.budgetessentials.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hozella.budgetessentials.MainActivity;
import com.hozella.budgetessentials.Model.Data;
import com.hozella.budgetessentials.Model.Expense;
import com.hozella.budgetessentials.Model.Income;
import com.hozella.budgetessentials.R;
import com.hozella.budgetessentials.SettingsActivity;

public class DashboardFragment extends Fragment {

    //Floating Action Button Variables
    private FloatingActionButton fab_main_btn;
    private FloatingActionButton fab_income_btn;
    private FloatingActionButton fab_expense_btn;
    private TextView fab_income_txt;
    private TextView fab_expense_txt;
    private boolean isOpen = false;
    private Animation fadeOpen, fadeClose;
    private Context context;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_dashboard, container, false);

        context = this.getContext();

        //Connect floating button to layout
        fab_main_btn = v.findViewById(R.id.fb_main_plus_btn);
        fab_income_btn = v.findViewById(R.id.income_ft_btn);
        fab_expense_btn = v.findViewById(R.id.expense_ft_btn);

        //Connect floating text to layout
        fab_income_txt = v.findViewById(R.id.income_ft_text);
        fab_expense_txt = v.findViewById(R.id.expense_ft_text);

        //Connect animation
        fadeOpen = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_open);
        fadeClose = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_close);

        // Spinner values for both Income and Expense
        String [] values = {"Weekly", "Bi-Weekly", "Monthly"};

        double te = 0.0;
        double ie = 0.0;

        for (Expense exp : Data.expenseList){
            te += exp.getAmount();
        }
        String totalExpense = String.valueOf(te);
        for (Income inc : Data.incomeList){
            ie += inc.getAmount();
        }
        String totalIncome = String.valueOf(ie);

        TextView totalExpText = v.findViewById(R.id.DashBoardExpenseAmountText);
        TextView totalIncText = v.findViewById(R.id.DashBoardIncomeAmountText);
        totalExpText.setText(totalExpense);
        totalIncText.setText(totalIncome);


        // Setup animation and clickable state after clicking the fab
        fab_main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isOpen){
                    fab_income_btn.startAnimation(fadeClose);
                    fab_expense_btn.startAnimation(fadeClose);
                    fab_expense_btn.setClickable(false);
                    fab_income_btn.setClickable(false);

                    fab_income_txt.startAnimation(fadeClose);
                    fab_expense_txt.startAnimation(fadeClose);
                    fab_income_txt.setClickable(false);
                    fab_expense_txt.setClickable(false);
                    isOpen=false;
                }else {
                    fab_income_btn.startAnimation(fadeOpen);
                    fab_expense_btn.startAnimation(fadeOpen);
                    fab_expense_btn.setClickable(true);
                    fab_income_btn.setClickable(true);

                    fab_income_txt.startAnimation(fadeOpen);
                    fab_expense_txt.startAnimation(fadeOpen);
                    fab_income_txt.setClickable(true);
                    fab_expense_txt.setClickable(true);
                    isOpen=true;
                }

                // Setup Income Occurrence Spinner
                /*Spinner spinner = (Spinner) v.findViewById(R.id.IncomeOccurrenceSpinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, values);
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinner.setAdapter(adapter);*/
            }
        });

        // Open the Income Data Insertion Dialog
        fab_income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                incomeDataInsert();
            }
        });

        // Open the Expense Data Insertion Dialog
        fab_expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                expenseDataInsert();
            }
        });

        Button settingsButton  = (Button) v.findViewById(R.id.settings_button);

        settingsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });


        return v;

    }

    private void incomeDataInsert(){
        //Set view to be the income dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.income_form, null);
        dialog.setView(v);
        AlertDialog dialog1 = dialog.create();

        //Link fields
        EditText incomeName = v.findViewById(R.id.name_field);
        EditText incomeAmount = v.findViewById(R.id.amount_field);
        EditText incomeNote = v.findViewById(R.id.note_field);
        Button  btnSaveIncome = v.findViewById(R.id.btnSaveIncome);
        Button btnCancelIncome = v.findViewById(R.id.btnCancelIncome);

        String [] values = {"Occurrence", "Weekly", "Bi-Weekly", "Monthly"};
        Spinner spinner = (Spinner) v.findViewById(R.id.IncomeOccurrenceSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnSaveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String incomeNameStr = incomeName.getText().toString();
                String incomeAmountStr = incomeAmount.getText().toString().trim();
                String incomeNoteStr = incomeNote.getText().toString();
                String incomeOcc = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(incomeNameStr)){
                    incomeName.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(incomeAmountStr)){
                    incomeAmount.setError("Required Field...");
                    return;
                }

                if (TextUtils.equals(incomeOcc, "Occurrence")){
                    TextView errorText = (TextView) spinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("You must set an Occurrence...");
                    return;
                }

                double incomeAmountInt = Double.parseDouble(incomeAmountStr);


                Income income = new Income(Data.getNextID(),incomeNameStr, incomeAmountInt, incomeNoteStr, incomeOcc);
                Data.incomeList.add(income);
                Data.saveData(context);
                Toast toast = Toast.makeText(getContext(), "Income Saved", Toast.LENGTH_SHORT);
                toast.show();
                dialog1.dismiss();

            }
        });

        btnCancelIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        dialog1.show();

    }

    private void expenseDataInsert(){
        //Set view to be the expense dialog
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View v = inflater.inflate(R.layout.expense_form, null);
        dialog.setView(v);
        AlertDialog dialog1 = dialog.create();

        //Link fields
        EditText expenseName = v.findViewById(R.id.name_field);
        EditText expenseAmount = v.findViewById(R.id.amount_field);
        EditText expenseNote = v.findViewById(R.id.note_field);
        Button  btnSaveExpense = v.findViewById(R.id.btnSaveExpense);
        Button btnCancelExpense = v.findViewById(R.id.btnCancelExpense);

        String [] values = {"Occurrence", "Weekly", "Bi-Weekly", "Monthly"};
        Spinner spinner = (Spinner) v.findViewById(R.id.ExpenseOccurrenceSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        btnSaveExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String expenseNameStr = expenseName.getText().toString();
                String expenseAmountStr = expenseAmount.getText().toString().trim();
                String expenseNoteStr = expenseNote.getText().toString();
                String expenseOcc = spinner.getSelectedItem().toString();

                if (TextUtils.isEmpty(expenseNameStr)){
                    expenseName.setError("Required Field...");
                    return;
                }

                if (TextUtils.isEmpty(expenseAmountStr)){
                    expenseAmount.setError("Required Field...");
                    return;
                }

                if (TextUtils.equals(expenseOcc, "Occurrence")){
                    TextView errorText = (TextView) spinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);
                    errorText.setText("You must set an Occurrence...");
                    return;
                }

                double expenseAmountInt = Double.parseDouble(expenseAmountStr);

                Expense expense = new Expense(Data.getNextID(), expenseNameStr, expenseAmountInt, expenseNoteStr, expenseOcc);
                Data.expenseList.add(expense);
                Data.saveData(context);
                Toast toast = Toast.makeText(getContext(), "Expense Saved", Toast.LENGTH_SHORT);
                toast.show();
                dialog1.dismiss();

            }
        });

        btnCancelExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });

        dialog1.show();



    }


}
