package com.example.vendecar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vendecar.entidades.Coche;

import java.util.ArrayList;

public class AdaptadorEliminar extends RecyclerView.Adapter<AdaptadorEliminar.ViewHolderCoche> {

    ArrayList<Coche> listaCoche;
    private static ArrayList<Integer> borrarCoches = new ArrayList<>();

    public static ArrayList<Integer> getBorrarCoches() {
        return borrarCoches;
    }


    public AdaptadorEliminar(ArrayList<Coche> listaCoche) {
        this.listaCoche = listaCoche;
    }

    @NonNull
    @Override
    public ViewHolderCoche onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_eliminar, null, false);
        return new ViewHolderCoche(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCoche holder, int position) {

        //La variable debe ser final al ser referencianda desde una clase interna
        final int id = listaCoche.get(position).getId();

        //Al pulsar en el checkbox recogemos el id de cada coche a través del recycler
        holder.cbEliminar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.isChecked()){
                borrarCoches.add(id);

            }



            }
        });
        holder.tvMarcaEliminar.setText(listaCoche.get(position).getMarca());
        holder.tvModeloEliminar.setText(listaCoche.get(position).getModelo());
        holder.tvKMEliminar.setText(listaCoche.get(position).getKM());
        holder.tvAnioEliminar.setText(listaCoche.get(position).getAnio());


    }

    @Override
    public int getItemCount() {
        return listaCoche.size();
    }

    public class ViewHolderCoche extends RecyclerView.ViewHolder {

        CheckBox cbEliminar;

        TextView tvMarcaEliminar, tvModeloEliminar, tvKMEliminar, tvAnioEliminar;

        public ViewHolderCoche(@NonNull View itemView) {
            super(itemView);

            cbEliminar = (CheckBox) itemView.findViewById(R.id.cbEliminar);
            tvMarcaEliminar = (TextView) itemView.findViewById(R.id.tvMarcaEliminar);
            tvModeloEliminar = (TextView) itemView.findViewById(R.id.tvModeloEliminar);
            tvKMEliminar = (TextView) itemView.findViewById(R.id.tvKmEliminar);
            tvAnioEliminar = (TextView) itemView.findViewById(R.id.tvAnioEliminar);

        }
    }
}
