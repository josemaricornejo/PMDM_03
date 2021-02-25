package com.example.vendecar;

import android.app.Activity;
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
}
