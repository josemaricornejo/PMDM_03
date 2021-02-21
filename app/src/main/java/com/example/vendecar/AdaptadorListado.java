package com.example.vendecar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendecar.entidades.Coche;

import java.util.ArrayList;

public class AdaptadorListado extends RecyclerView.Adapter<AdaptadorListado.ViewHolderCoche> {

    ArrayList<Coche> listaCoche;

    public AdaptadorListado(ArrayList<Coche> listaCoche) {
        this.listaCoche = listaCoche;
    }

    @NonNull
    @Override
    public ViewHolderCoche onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_listado, null, false);
        return new ViewHolderCoche(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCoche holder, int position) {

        //holder.ivCoche.setImageResource(listaCoche.get(position).getFoto());
        holder.tvMarca.setText(listaCoche.get(position).getMarca());
        holder.tvModelo.setText(listaCoche.get(position).getModelo());
        holder.tvKM.setText(listaCoche.get(position).getKM());
        holder.tvAnio.setText(listaCoche.get(position).getAnio());
        holder.tvPrecio.setText(listaCoche.get(position).getPrecio());

    }

    @Override
    public int getItemCount() {
        return listaCoche.size();
    }

    public class ViewHolderCoche extends RecyclerView.ViewHolder {

        ImageView ivCoche;
        TextView tvMarca, tvModelo, tvKM, tvAnio, tvPrecio;

        public ViewHolderCoche(@NonNull View itemView) {
            super(itemView);

            ivCoche = (ImageView) itemView.findViewById(R.id.ivCoche);
            tvMarca = (TextView) itemView.findViewById(R.id.tvMarcaListado);
            tvModelo = (TextView) itemView.findViewById(R.id.tvModeloListado);
            tvKM = (TextView) itemView.findViewById(R.id.tvKMListado);
            tvAnio = (TextView) itemView.findViewById(R.id.tvAnioListado);
            tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecioListado);
        }
    }
}
