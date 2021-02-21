package com.example.vendecar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vendecar.entidades.Coche;
import com.example.vendecar.utilidades.RellenarArrayCoches;

import java.util.ArrayList;

public class ListadoCoches_Activity extends AppCompatActivity {

    ArrayList<Coche> listaCoche;
    RecyclerView recyclerCoche;

    ConexionSQLiteHelper conn;

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

        //llenarCoches();
        listaCoche=RellenarArrayCoches.llenarCoches(getApplicationContext());

        if(listaCoche.size()==0){
            Toast.makeText(this,"Debe introducir coches", Toast.LENGTH_SHORT).show();
        }
        //Clase AdaptadorCoche con el arrayList pasado por parámetros al constructor
        AdaptadorListado adapter = new AdaptadorListado(listaCoche);
        //Le pasamos al RecyclerView el adaptador creado
        recyclerCoche.setAdapter(adapter);
    }
    /*
    private void llenarCoches() {
        //Con esta instrucción podemos leer de la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();
        Coche coche = null;
        //String[] campos={Utilidades.CAMPO_MARCA, Utilidades.CAMPO_MODELO, Utilidades.CAMPO_ANIO, Utilidades.CAMPO_KM, Utilidades.CAMPO_CC, Utilidades.CAMPO_CV, Utilidades.CAMPO_PRECIO};

        //Genereamos el cursor
        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COCHE,null);

        //Cursor cursor = db.query(Utilidades.TABLA_COCHE, campos, null, null, null, null, null);

        //Con el resultado de la consulta hacemos el recorrido de los datos obtenidos.
        //Con este estamos diciendo que el primer registro lo mapee en los diferentes elementos de nuestro objeto.


        cursor.moveToFirst();

            do{
                coche = new Coche();
                coche.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                coche.setMarca(cursor.getString(cursor.getColumnIndex("marca")));
                coche.setModelo(cursor.getString(cursor.getColumnIndex("modelo")));
                coche.setAnio(cursor.getString(cursor.getColumnIndex("anio")));
                coche.setKM(cursor.getString(cursor.getColumnIndex("km")));
                coche.setCC(cursor.getString(cursor.getColumnIndex("cc")));
                coche.setCV(cursor.getString(cursor.getColumnIndex("cv")));
                coche.setPrecio(cursor.getString(cursor.getColumnIndex("precio")));
                coche.setVendido(Integer.parseInt(cursor.getString(cursor.getColumnIndex("vendido"))));
                //Guardamos el primer registro en nuestra lista coches.
                listaCoche.add(coche);
            }while(cursor.moveToNext());

        //Cerramos el cursor
        cursor.close();


    }

     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//Método que muestra el menú craedo en esta activity
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id== R.id.preferencias){
          Intent miIntent = new Intent(ListadoCoches_Activity.this, Preferncias_Activity.class);
          startActivity(miIntent);
       }
        if(id== R.id.aniadir_coche){
            Intent miIntent = new Intent(ListadoCoches_Activity.this, Insertar_Activity.class);
            startActivity(miIntent);
        }
        if(id== R.id.eliminar_coche){
            Intent miIntent = new Intent(ListadoCoches_Activity.this, Eliminar_Activity.class);
            startActivity(miIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}