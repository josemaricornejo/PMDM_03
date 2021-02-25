package com.example.vendecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vendecar.entidades.Coche;
import com.example.vendecar.utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ficha_Activity extends AppCompatActivity {

    Spinner spMarca;
    EditText etModelo, etKM, etAnio, etCC, etCV, etPrecio;
    CheckBox cbVendido;
    String marca;
    ConexionSQLiteHelper conn;

    //Array para almacenar la lista de marcas de coches
    ArrayList<String> marcas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha);

        //Creamos conexión a la base de datos
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_coche", null, 1);

        spMarca = (Spinner) findViewById(R.id.spMarcaFicha);
        etModelo = (EditText) findViewById(R.id.etModeloFicha);
        etKM = (EditText) findViewById(R.id.etKmFicha);
        etAnio = (EditText) findViewById(R.id.etAnioFicha);
        etCC = (EditText) findViewById(R.id.etCcFicha);
        etCV = (EditText) findViewById(R.id.etCvFicha);
        etPrecio = (EditText) findViewById(R.id.etPrecioFicha);
        cbVendido = (CheckBox) findViewById(R.id.cbVendidoFicha);




        //Lectura de archivo de marcas
        String archivo = "marcas.txt";

        try {
            FileInputStream fileInputStream = null;

            fileInputStream = openFileInput(archivo);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
            while((lineaTexto=bufferedReader.readLine())!=null){
                marcas.add(lineaTexto);
                //System.out.println(lineaTexto);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, marcas);

        spMarca.setAdapter(adapter);

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                marca=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Obtenemos el coche de la base de datos
        completarFichaCoche();

    }

    private void completarFichaCoche() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_coche", null, 1);
        //Con esta instrucción podemos leer de la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametros = {String.valueOf(AdaptadorListado.getId())};
        String[] campos = {Utilidades.CAMPO_MARCA, Utilidades.CAMPO_MODELO, Utilidades.CAMPO_ANIO, Utilidades.CAMPO_KM,
        Utilidades.CAMPO_CC, Utilidades.CAMPO_CV, Utilidades.CAMPO_PRECIO, Utilidades.CAMPO_VENDIDO};
        Coche coche = null;

        //Genereamos el cursor
        Cursor cursor = db.query(Utilidades.TABLA_COCHE, campos, Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
        cursor.moveToFirst();

        //Obtenemos la marca de la base de datos
        String marcaSeleccionada = cursor.getString(cursor.getColumnIndex("marca"));
        //Obtenemos el indice en el array de marcas asociado a la marca
        int posicionSeleccionada = marcas.indexOf(marcaSeleccionada);

        //Establecemos la marca en el spinner
        spMarca.setSelection(posicionSeleccionada);
        etModelo.setText(cursor.getString(cursor.getColumnIndex("modelo")));
        etAnio.setText(cursor.getString(cursor.getColumnIndex("anio")));
        etKM.setText(cursor.getString(cursor.getColumnIndex("km")));
        etCC.setText(cursor.getString(cursor.getColumnIndex("cc")));
        etCV.setText(cursor.getString(cursor.getColumnIndex("cv")));
        etPrecio.setText(cursor.getString(cursor.getColumnIndex("precio")));
        if(cursor.getString(cursor.getColumnIndex("vendido")).equals("1")){
            cbVendido.setChecked(true);
        }else{
            cbVendido.setChecked(false);
        }


        cursor.close();



    }






    /*
    public void onClick(View view) {
        registrarCoche();
    }


    private void registrarCoche() {
        //Creamos la conexión a la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_coche", null, 1);
        //Abro la base de datos para poder editarla
        SQLiteDatabase db=conn.getWritableDatabase();
        //Android proporciona ContentValue que es un forma rápida de hacer los registros similar a como se utilizan los HashMap con una clave y valor asociados.
        ContentValues values = new ContentValues();
        //Introducimos en el put() una clave y un valor asociados a los campos de nuestra base de datos.
        //values.put(Utilidades.CAMPO_MARCA, spMarca.get);
        values.put(Utilidades.CAMPO_MODELO, etModelo.getText().toString());
        values.put(Utilidades.CAMPO_KM, etKM.getText().toString());
        values.put(Utilidades.CAMPO_ANIO, etAnio.getText().toString());
        values.put(Utilidades.CAMPO_CC, etCC.getText().toString());
        values.put(Utilidades.CAMPO_CV, etKM.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO, etPrecio.getText().toString());
        values.put(Utilidades.CAMPO_VENDIDO, cbVendido.isSelected());

    }

     */


    public void onClickVolver(View view) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametros = {String.valueOf(AdaptadorListado.getId())};

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_MARCA, marca);
        values.put(Utilidades.CAMPO_MODELO, etModelo.getText().toString());
        values.put(Utilidades.CAMPO_KM, etKM.getText().toString());
        values.put(Utilidades.CAMPO_ANIO, etAnio.getText().toString());
        values.put(Utilidades.CAMPO_CC, etCC.getText().toString());
        values.put(Utilidades.CAMPO_CV, etKM.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO, etPrecio.getText().toString());
        values.put(Utilidades.CAMPO_VENDIDO, cbVendido.isSelected());

        //Este método se encarga de realizar el proceso de actualización
        db.update(Utilidades.TABLA_COCHE,values,Utilidades.CAMPO_ID+"=?",parametros);

        Toast.makeText(getApplicationContext(),"Coche con id " + AdaptadorListado.getId() + " actualizado", Toast.LENGTH_SHORT).show();
        db.close();

        Intent miIntent = new Intent(Ficha_Activity.this, Listado_Activity.class);
        startActivity(miIntent);
    }
}