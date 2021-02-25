package com.example.vendecar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendecar.entidades.Coche;

import java.util.ArrayList;

public class AdaptadorListado extends RecyclerView.Adapter<AdaptadorListado.ViewHolderCoche> {

    //Ene esta lista almacenamos los objetos coche que utilizaremos en el adaptador para representarlos
    ArrayList<Coche> listaCoche;
    private static int idCoche;

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

        if(listaCoche.get(position).getVendido()==1){
            System.out.println("vendido");
            holder.ivCoche.setImageResource(R.drawable.vendido);
        }else{
            holder.ivCoche.setImageResource(R.drawable.no_vendido);
        }

        //holder.ivCoche.setImageResource(listaCoche.get(position).getFoto());
        holder.tvMarca.setText(listaCoche.get(position).getMarca());
        holder.tvModelo.setText(listaCoche.get(position).getModelo());
        holder.tvKM.setText(listaCoche.get(position).getKM());
        holder.tvAnio.setText(listaCoche.get(position).getAnio());
        holder.tvPrecio.setText(listaCoche.get(position).getPrecio());

        //La variable debe ser final al ser referencianda desde una clase interna
        final int id = listaCoche.get(position).getId();

        //Conseguimos que al hacer click en un coche vaya a la pantalla de ficha para actualizar
        holder.linearListado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCoche=id;

                Intent miIntent = new Intent(v.getContext(), Ficha_Activity.class);
                v.getContext().startActivity(miIntent);

            }
        });

    }

    public static int getId() {
        return idCoche;
    }

    @Override
    public int getItemCount() {
        return listaCoche.size();
    }

    public class ViewHolderCoche extends RecyclerView.ViewHolder {

        ImageView ivCoche;
        TextView tvMarca, tvModelo, tvKM, tvAnio, tvPrecio;
        LinearLayout linearListado;

        public ViewHolderCoche(@NonNull View itemView) {
            super(itemView);

            linearListado = (LinearLayout) itemView.findViewById((R.id.linearListado));
            ivCoche = (ImageView) itemView.findViewById(R.id.ivCocheListado);
            tvMarca = (TextView) itemView.findViewById(R.id.tvMarcaListado);
            tvModelo = (TextView) itemView.findViewById(R.id.tvModeloListado);
            tvKM = (TextView) itemView.findViewById(R.id.tvKMListado);
            tvAnio = (TextView) itemView.findViewById(R.id.tvAnioListado);
            tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecioListado);
        }
    }
}
