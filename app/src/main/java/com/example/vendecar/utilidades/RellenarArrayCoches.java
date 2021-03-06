package com.example.vendecar.utilidades;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vendecar.ConexionSQLiteHelper;
import com.example.vendecar.entidades.Coche;

import java.util.ArrayList;
/*
    Clase con un método que nos devuelve una lista con los coches que hay en la base de datos.
 */

public class RellenarArrayCoches {

    private static Cursor cursor;


    public static ArrayList<Coche> llenarCoches(Context context, String valor) {

        //ArrayList que almacena objetos de la clase Coche con los datos tomados de la base de datos
        ArrayList<Coche> listaCoche=new ArrayList<>();
        //Creamos conexión a la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(context, "bd_coche", null, 1);
        //Con esta instrucción podemos leer de la base de datos
        SQLiteDatabase db = conn.getReadableDatabase();
        Coche coche = null;

        String[] param = {"vendido"};




        System.out.println("El valor d evalore es:"+valor);
        if(valor.equals("0")){

            //Generamos el cursor
            cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_COCHE,null);
        }else if (valor.equals("1")){
            //Generamos el cursor
            cursor = db.rawQuery("SELECT * FROM "+Utilidades.TABLA_COCHE+" WHERE vendido=0",null);
        }







        //Con el resultado de la consulta hacemos el recorrido de los datos obtenidos.
        //Con este estamos diciendo que el primer registro lo mapee en los diferentes elementos de nuestro objeto.
    if(cursor!=null) {

        if (cursor.moveToFirst()) {

            do {
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
            } while (cursor.moveToNext());

        }

        //Cerramos el cursor
        cursor.close();

    }



        return listaCoche;


    }

}
