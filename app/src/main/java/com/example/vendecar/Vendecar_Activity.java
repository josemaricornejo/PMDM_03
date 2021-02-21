package com.example.vendecar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Vendecar_Activity extends AppCompatActivity {

    ImageView ivCoche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendecar);

        ivCoche = (ImageView) findViewById(R.id.ivCoche);

        //Instancia a la clase conexión de la base de datos
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_coche", null, 1);
    }

    public void onClick(View view) {

        Intent miIntent = new Intent(Vendecar_Activity.this, ListadoCoches_Activity.class);
        startActivity(miIntent);
    }
}