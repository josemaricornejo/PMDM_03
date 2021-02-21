package com.example.vendecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vendecar.utilidades.Utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Ficha_Actualizar_Activity extends AppCompatActivity {

    Spinner spMarca;
    EditText etModelo, etKM, etAnio, etCC, etCV, etPrecio;
    CheckBox cbVendido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha);

        spMarca = (Spinner) findViewById(R.id.spMarca_2);
        etModelo = (EditText) findViewById(R.id.etModelo_2);
        etKM = (EditText) findViewById(R.id.etKM_2);
        etAnio = (EditText) findViewById(R.id.etAnio_2);
        etCC = (EditText) findViewById(R.id.etCC_2);
        etCV = (EditText) findViewById(R.id.etCV_2);
        etPrecio = (EditText) findViewById(R.id.etPrecio_2);
        cbVendido = (CheckBox) findViewById(R.id.cbVendido_2);


        //Array para almacenar la lista de marcas de coches
        ArrayList<String> marcas = new ArrayList<>();

        //Lectura de archivo de marcas
        String archivo = "marcas.txt";
        try {
            //Abrimos el stream entre nuestro programa y el exterior, el fichero debe existir
            FileReader f = new FileReader(archivo);
            //Envolvemos el filereader en un buffer
            BufferedReader b = new BufferedReader(f);

            while((archivo=b.readLine())!=null){
                marcas.add(archivo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        ArrayAdapter<CharSequence> adapter= new ArrayAdapter(this, android.R.layout.simple_spinner_item, marcas);

        spMarca.setAdapter(adapter);

        spMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Seleccionado: " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

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


}