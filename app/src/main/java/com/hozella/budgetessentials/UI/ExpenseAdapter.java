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

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>{

    String[] labels, occurrences, notes;
    Double[] amounts;

    public ExpenseAdapter(String[] labels, Double[] amounts, String[] occurrences, String[] notes){

        this.labels = labels;
        this.occurrences = occurrences;
        this.notes = notes;
        this.amounts = amounts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder((LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_row, parent, false)));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.labelText.setText(labels[position]);
        holder.amountText.setText("$" + amounts[position].toString());
        holder.occurrenceText.setText(occurrences[position]);
        //holder.noteText.setText(notes[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(view.getContext(), "Tapped on: " + position + " " + holder.labelText.getText(), Toast.LENGTH_SHORT);
                toast.show();

                Data.expenseList.remove(position);
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
        TextView noteText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            labelText = itemView.findViewById(R.id.label_txt);
            amountText = itemView.findViewById(R.id.amount_txt);
            occurrenceText = itemView.findViewById(R.id.occurrence_txt);

        }
    }
}
