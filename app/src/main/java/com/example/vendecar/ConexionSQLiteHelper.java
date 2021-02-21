package com.example.vendecar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.vendecar.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    //Al llamar a este constructor creamos la base de datos
    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //Automáticamente se llama a este método que se encarga de generar los scripts con las tablas de nuestras entidades
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos la tabla coche
        db.execSQL(Utilidades.CREAR_TABLA_COCHE);
    }
    //Cada vez que ejecutamos la aplicación refrescamos los scripts volviendo a generar las tablas
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Si se instala la aplicación y encuentra una versión antigua la elimina y genera la nueva.
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_COCHE);
        onCreate(db);
    }
}
