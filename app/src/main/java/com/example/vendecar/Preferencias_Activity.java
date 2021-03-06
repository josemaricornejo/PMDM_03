package com.example.vendecar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

//Clase que llama al Fragment de las preferencias

public class Preferencias_Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new Preferencias_Fragment())
                .commit();
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent miIntent = new Intent(Preferencias_Activity.this, Listado_Activity.class);
        startActivity(miIntent);
        finish();
    }


}
