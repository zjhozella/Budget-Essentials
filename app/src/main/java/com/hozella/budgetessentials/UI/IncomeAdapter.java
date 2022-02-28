package com.hozella.budgetessentials.UI;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hozella.budgetessentials.Model.Data;
import com.hozella.budgetessentials.R;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.MyViewHolder>{

    String[] labels, occurrences, notes;
    Double[] amounts;

    public IncomeAdapter(String[] labels, Double[] amounts, String[] occurrences, String[] notes){

        this.labels = labels;
        this.occurrences = occurrences;
        this.notes = notes;
        this.amounts = amounts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.income_row, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.labelText.setText(labels[position]);
        holder.amountText.setText("$" + amounts[position].toString());
        holder.occurrenceText.setText(occurrences[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), "Tapped on: " + position + " " + holder.labelText.getText(), Toast.LENGTH_SHORT);
                toast.show();

                Data.incomeList.remove(position);
                notifyItemRemoved(position);
                Data.saveData(view.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return labels.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView labelText;
        TextView amountText;
        TextView occurrenceText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelText = itemView.findViewById(R.id.Ilabel_txt);
            amountText = itemView.findViewById(R.id.Iamount_txt);
            occurrenceText = itemView.findViewById(R.id.Ioccurrence_txt);
        }
    }
}
