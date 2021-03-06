package com.example.vendecar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.vendecar.entidades.Coche;
import com.example.vendecar.utilidades.RellenarArrayCoches;
import com.example.vendecar.utilidades.Utilidades;

import java.util.ArrayList;

public class Eliminar_Activity extends AppCompatActivity {

    ArrayList<Coche> listaCoche;
    RecyclerView recyclerCoche;

    ConexionSQLiteHelper conn;

    CheckBox cbEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eliminar);

        cbEliminar = (CheckBox) findViewById(R.id.cbEliminar);

        //Creamos conexión a la base de datos
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_coche", null, 1);
        //Creamos el ArrayList que contendrá los coches y el recycler que lo mostrará
        listaCoche = new ArrayList<>();
        recyclerCoche = (RecyclerView) findViewById(R.id.rcEliminar);
        recyclerCoche.setLayoutManager(new LinearLayoutManager(this));

        //Llenamos el arraylist
        listaCoche= RellenarArrayCoches.llenarCoches(getApplicationContext(), "0");

        //Clase AdaptadorCoche con el arrayList pasado por parámetros al constructor
        AdaptadorEliminar adapter = new AdaptadorEliminar(listaCoche);
        //Le pasamos al RecyclerView el adaptador creado
        recyclerCoche.setAdapter(adapter);

    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent miIntent = new Intent(Eliminar_Activity.this, Listado_Activity.class);
        startActivity(miIntent);
        finish();
    }



    public void onClickEliminar(View view) {

        //Obtenemos la conexión
        SQLiteDatabase db = conn.getWritableDatabase();

        //Obtenemos un id con los id de los coches a borrar
        ArrayList<Integer> posicion = AdaptadorEliminar.getBorrarCoches();

        //Recorremos el array de id borrando los coches
        for(Integer p: posicion){

            //Almacenamos en este string el id del coche que queremos eliminar y lo cogemos del AdaptadorEliminar
            String[] parametros={String.valueOf(p)};

            //Método encargado de eliminar el registro, tabla que queremos eliminar, el campo de la consulta y el campo
            db.delete(Utilidades.TABLA_COCHE, Utilidades.CAMPO_ID+"=?",parametros);

            Toast.makeText(this,"Se elimino el coche", Toast.LENGTH_SHORT).show();

        }


        db.close();

    }
}