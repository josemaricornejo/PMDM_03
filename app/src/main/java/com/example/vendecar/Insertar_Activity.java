package com.example.vendecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
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
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Insertar_Activity extends AppCompatActivity {

    Spinner spMarca;
    EditText etModelo, etKM, etAnio, etCC, etCV, etPrecio;
    CheckBox cbVendido;
    String marca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar);

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

    }



    private void registrarCoche() {

        //Creamos la conexión a la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_coche", null, 1);


        //Abro la base de datos para poder editarla
        SQLiteDatabase db=conn.getWritableDatabase();

        //conn.onUpgrade(db,1,2);


        //Android proporciona ContentValue que es un forma rápida de hacer los registros similar a como se utilizan los HashMap con una clave y valor asociados.
        ContentValues values = new ContentValues();
        //Introducimos en el put() una clave y un valor asociados a los campos de nuestra base de datos.
        values.put(Utilidades.CAMPO_MARCA, marca);
        values.put(Utilidades.CAMPO_MODELO, etModelo.getText().toString());
        values.put(Utilidades.CAMPO_KM, etKM.getText().toString());
        values.put(Utilidades.CAMPO_ANIO, etAnio.getText().toString());
        values.put(Utilidades.CAMPO_CC, etCC.getText().toString());
        values.put(Utilidades.CAMPO_CV, etKM.getText().toString());
        values.put(Utilidades.CAMPO_PRECIO, etPrecio.getText().toString());


        if(cbVendido.isChecked()){
            System.out.println("valor 1");
            values.put(Utilidades.CAMPO_VENDIDO,"1".toString());
        }else{
            System.out.println("valor 0");
            values.put(Utilidades.CAMPO_VENDIDO,"0".toString());
        }




        //Insertamos lo anterior en la base de datos que devuelve un Long dependiendo de lo que le enviemos
        //En los parámetros el nombre de nuestra tabla, seguido del campo que queremos que nos devuelva y por último los valores que estamos ingresando
        long idResultante = db.insert(Utilidades.TABLA_COCHE, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Coche insertado con id: " + idResultante, Toast.LENGTH_LONG).show();


    }

    public void onClickRegistrar(View view) {
        registrarCoche();

    }
}