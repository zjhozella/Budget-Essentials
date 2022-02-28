package com.hozella.budgetessentials.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hozella.budgetessentials.Model.Data;
import com.hozella.budgetessentials.R;
import com.hozella.budgetessentials.UI.ExpenseAdapter;

import java.util.Arrays;

public class ExpenseFragment extends Fragment {
    
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String[] labels = new String[Data.expenseList.size()];
        String[] occurrences = new String[Data.expenseList.size()];
        String[] notes = new String[Data.expenseList.size()];
        Double[] amounts = new Double[Data.expenseList.size()];

        // Show expense fragment
        View v = inflater.inflate(R.layout.fragment_expense, container, false);

        // Set recyclerView to be equal to the one in the expense fragment
        //recyclerView = this.getActivity().findViewById(R.id.expenseRV);



        // Setting the items to be shown in the recyclerView
        for (int i = 0; i < Data.expenseList.size(); ++i){
            //String[] labelList = new String[Data.incomeList.size()];
            labels[i] = Data.expenseList.get(i).getLabel();
        }

        for (int i = 0; i < Data.expenseList.size(); ++i){
            amounts[i] = Data.expenseList.get(i).getAmount();
        }

        for (int i = 0; i < Data.expenseList.size(); ++i){
            occurrences[i] = Data.expenseList.get(i).getOccurrence();
        }

        for (int i = 0; i < Data.expenseList.size(); ++i){
            notes[i] = Data.expenseList.get(i).getNote();
        }

        RecyclerView recyclerView = v.findViewById((R.id.expenseRV));
        recyclerView.setLayoutManager(new LinearLayoutManager((this.getContext())));
        RecyclerView.Adapter adapter = new ExpenseAdapter(labels, amounts, occurrences, notes);
        recyclerView.setAdapter(adapter);



        // Setting the recycle view adapter
        //RVAdapter rvAdapter = new RVAdapter(this.getContext(), labels, amounts, occurrences, notes);
        //recyclerView.setAdapter(rvAdapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        return v;
    }
}
