package com.hozella.budgetessentials.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hozella.budgetessentials.Model.Data;
import com.hozella.budgetessentials.R;
import com.hozella.budgetessentials.UI.ExpenseAdapter;
import com.hozella.budgetessentials.UI.IncomeAdapter;

public class IncomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] labels = new String[Data.incomeList.size()];
        String[] occurrences = new String[Data.incomeList.size()];
        String[] notes = new String[Data.incomeList.size()];
        Double[] amounts = new Double[Data.incomeList.size()];

        // Show income fragment
        View v = inflater.inflate(R.layout.fragment_income, container, false);



        // Setting the items to be shown in the recyclerView
        for (int i = 0; i < Data.incomeList.size(); ++i){
            labels[i] = Data.incomeList.get(i).getLabel();
        }

        for (int i = 0; i < Data.incomeList.size(); ++i){
            amounts[i] = Data.incomeList.get(i).getAmount();
        }

        for (int i = 0; i < Data.incomeList.size(); ++i){
            occurrences[i] = Data.incomeList.get(i).getOccurrence();
        }

        for (int i = 0; i < Data.incomeList.size(); ++i){
            notes[i] = Data.incomeList.get(i).getNote();
        }

        RecyclerView recyclerView = v.findViewById((R.id.incomeRV));
        recyclerView.setLayoutManager(new LinearLayoutManager((this.getContext())));
        RecyclerView.Adapter adapter = new IncomeAdapter(labels, amounts, occurrences, notes);
        recyclerView.setAdapter(adapter);

        return v;


        //return inflater.inflate(R.layout.fragment_income, container, false);
    }
}
