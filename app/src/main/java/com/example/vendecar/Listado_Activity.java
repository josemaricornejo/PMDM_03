package com.example.vendecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vendecar.entidades.Coche;
import com.example.vendecar.utilidades.RellenarArrayCoches;

import java.util.ArrayList;

public class Listado_Activity extends AppCompatActivity {

    ArrayList<Coche> listaCoche;
    RecyclerView recyclerCoche;

    ConexionSQLiteHelper conn;
    String valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado);

        //Creamos conexión a la base de datos
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_coche", null, 1);
        //Creamos el ArrayList que contendrá los coches y el recycler que lo mostrará
        listaCoche = new ArrayList<>();
        recyclerCoche = (RecyclerView) findViewById(R.id.rcListado);
        recyclerCoche.setLayoutManager(new LinearLayoutManager(this));

        //Obtenemos el valor seleccionado en las preferencias en la variable global "valor" que se la pasamos por parámetro al siguiente método
        obtenerPreferencias();

        //Obtenemos un lista con los coches que se encuentran en la base de datos
        listaCoche=RellenarArrayCoches.llenarCoches(getApplicationContext(), valor);



        if(listaCoche.size()==0){
            Toast.makeText(this,"Debe introducir coches", Toast.LENGTH_SHORT).show();
        }else{
            //Clase AdaptadorCoche con el arrayList pasado por parámetros al constructor
            AdaptadorListado adapter = new AdaptadorListado(listaCoche);
            //Le pasamos al RecyclerView el adaptador creado
            recyclerCoche.setAdapter(adapter);

        }

    }

    public ConexionSQLiteHelper getConn() {
        return conn;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Método que muestra el menú craedo en esta activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id== R.id.preferencias){
          Intent miIntent = new Intent(Listado_Activity.this, Preferencias_Activity.class);
          startActivity(miIntent);
          finish();
       }
        if(id== R.id.aniadir_coche){
            Intent miIntent = new Intent(Listado_Activity.this, Insertar_Activity.class);
            startActivity(miIntent);
            finish();
        }
        if(id== R.id.eliminar_coche){
            Intent miIntent = new Intent(Listado_Activity.this, Eliminar_Activity.class);
            startActivity(miIntent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void obtenerPreferencias() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        valor = sharedPreferences.getString("filtro", "0");
    }
}