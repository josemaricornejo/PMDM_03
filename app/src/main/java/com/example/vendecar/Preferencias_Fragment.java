package com.example.vendecar;

import android.os.Bundle;

import androidx.preference.PreferenceFragment;
import androidx.preference.PreferenceFragmentCompat;

import com.example.vendecar.R;

public class Preferencias_Fragment extends PreferenceFragment {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        //Sobreescribimos el método OnCreate() y llamamos la método setPreferencesFrom resource() para cargar el fichero XML
        //que contiene el diseño de la pantalla de preferencias.
        setPreferencesFromResource(R.xml.preferencias, rootKey);

    }

}
