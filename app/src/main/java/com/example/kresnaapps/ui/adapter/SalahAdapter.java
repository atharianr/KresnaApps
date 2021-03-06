package com.example.kresnaapps.ui.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kresnaapps.R;
import com.example.kresnaapps.databinding.NilaiPerSoalRowBinding;

import java.util.ArrayList;

public class SalahAdapter extends RecyclerView.Adapter<SalahAdapter.ViewHolder> {

    private ArrayList<Integer> arraySalah;

    public SalahAdapter(ArrayList<Integer> arraySalah) {
        this.arraySalah = arraySalah;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nilai_per_soal_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalahAdapter.ViewHolder holder, int position) {

        holder.binding.tvSoalKe.setText("Soal - " + (position + 1) + ": ");
        holder.binding.tvSalah.setText(String.valueOf(arraySalah.get(position)));

    }

    @Override
    public int getItemCount() {
        return arraySalah.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final NilaiPerSoalRowBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = NilaiPerSoalRowBinding.bind(itemView);
        }
    }
}
