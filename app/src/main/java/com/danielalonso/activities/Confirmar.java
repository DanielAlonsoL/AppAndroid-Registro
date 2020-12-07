package com.danielalonso.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Confirmar extends AppCompatActivity {

    private Bundle extras;
    private MaterialTextView set_nombre;
    private MaterialTextView set_num_cuenta;
    private MaterialTextView set_fecha_nacimiento;
    private MaterialTextView set_carrera;

    private MaterialButton editar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        extras = getIntent().getExtras();

        //String nombreCompleto = extras.getString(getString(R.string.p_nombre)) + " " + extras.getString(getString(R.string.p_apellidos));
        //String numCuenta = extras.getString(getString(R.string.p_num_cuenta));
        String fechaNacimiento = extras.getString(getString(R.string.p_fecha_nacimiento));
        //String carrera = extras.getString(getString(R.string.p_carrera));

        //set_nombre = (MaterialTextView) findViewById(R.id.tvNombreCompleto);
        //set_num_cuenta = (MaterialTextView) findViewById(R.id.tvNumCuenta);
        set_fecha_nacimiento = (MaterialTextView) findViewById(R.id.tvEdad);
        //set_carrera = (MaterialTextView) findViewById(R.id.tvCarrera);


        //set_nombre.setText(nombreCompleto);
        //set_num_cuenta.setText(numCuenta);
        set_fecha_nacimiento.setText(fechaNacimiento);
        //set_carrera.setText(carrera);


        editar = (MaterialButton) findViewById(R.id.btnEditar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
