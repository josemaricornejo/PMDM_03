package com.example.vendecar.utilidades;

public class Utilidades {

    //Constantes campos tabla coche, son los nombres de los campos en constantes
    public static final String TABLA_COCHE="coche";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_MARCA="marca";
    public static final String CAMPO_MODELO="modelo";
    public static final String CAMPO_ANIO="anio";
    public static final String CAMPO_KM="km";
    public static final String CAMPO_CC="cc";
    public static final String CAMPO_CV="cv";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_VENDIDO="vendido";

    //Constante con el script necesario para crear la tabla
    //public static final String CREAR_TABLA_COCHE="CREATE TABLE "+ TABLA_COCHE +" ("+CAMPO_ID+" PRIMARY KEY AUTOINCREMENT, "+CAMPO_MARCA+" TEXT, "+CAMPO_MODELO+" TEXT, "+CAMPO_ANIO+" TEXT,"+CAMPO_KM+
    //        " TEXT, " +CAMPO_CC + " TEXT, " + CAMPO_CV + " TEXT, "+CAMPO_PRECIO+" TEXT," + CAMPO_VENDIDO + " TEXT)";

    public static final String CREAR_TABLA_COCHE="CREATE TABLE "+ TABLA_COCHE +" ("+CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_MARCA+" TEXT, "+CAMPO_MODELO+" TEXT, "+CAMPO_ANIO+" TEXT,"+CAMPO_KM+
            " TEXT, " +CAMPO_CC + " TEXT, " + CAMPO_CV + " TEXT, "+CAMPO_PRECIO+" TEXT," + CAMPO_VENDIDO + " TEXT)";


    //public static final String CREATE_TABLA_COCHE=" CREATE TABLE coche(marca TEXT, modelo TEXT, anio TEXT, km TEXT, cc TEXT, cv TEXT, precio TEXT)";


}
