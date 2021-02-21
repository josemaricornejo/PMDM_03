package com.example.vendecar.entidades;

public class Coche {

    private int id;
    private String marca;
    private String modelo;
    private String anio;
    private String KM;
    private String CC;
    private String CV;
    private String precio;
    private int vendido;

    public Coche() {
    }

    public Coche(int id, String marca, String modelo, String anio, String KM, String CC, String CV, String precio, int vendido) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.KM = KM;
        this.CC = CC;
        this.CV = CV;
        this.precio = precio;
        this.vendido = vendido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getKM() {
        return KM;
    }

    public void setKM(String KM) {
        this.KM = KM;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getVendido() {
        return vendido;
    }

    public void setVendido(int vendido) {
        this.vendido = vendido;
    }
}
