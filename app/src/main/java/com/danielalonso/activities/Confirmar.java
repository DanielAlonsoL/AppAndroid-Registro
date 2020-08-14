package com.danielalonso.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

public class Confirmar extends AppCompatActivity {

    private Bundle extras;
    private MaterialTextView set_nombre;
    private MaterialTextView set_fecha_nacimiento;
    private MaterialTextView set_telefono;
    private MaterialTextView set_email;
    private MaterialTextView set_descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);

        extras = getIntent().getExtras();

        String nombreCompleto = extras.getString(getString(R.string.p_nombre));
        String fechaNacimiento = extras.getString(getString(R.string.p_fecha));
        String telefono = extras.getString(getString(R.string.p_telefono));
        String email = extras.getString(getString(R.string.p_email));
        String descripcion = extras.getString(getString(R.string.p_descripcion));


        set_nombre = (MaterialTextView) findViewById(R.id.muestraNombre);
        set_fecha_nacimiento = (MaterialTextView) findViewById(R.id.muestraFechaNacimiento);
        set_telefono = (MaterialTextView) findViewById(R.id.muestraTelefono);
        set_email = (MaterialTextView) findViewById(R.id.muestraEmail);
        set_descripcion = (MaterialTextView) findViewById(R.id.muestraDescripcion);

        set_nombre.setText(nombreCompleto);
        set_fecha_nacimiento.setText(fechaNacimiento);
        set_telefono.setText(telefono);
        set_email.setText(email);
        set_descripcion.setText(descripcion);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void setConfirma() {
        extras = getIntent().getExtras();

        String nombreCompleto = extras.getString(getString(R.string.p_nombre));
        String fechaNacimiento = extras.getString(getString(R.string.p_fecha));
        String telefono = extras.getString(getString(R.string.p_telefono));
        String email = extras.getString(getString(R.string.p_email));
        String descripcion = extras.getString(getString(R.string.p_descripcion));


        set_nombre = (MaterialTextView) findViewById(R.id.muestraNombre);
        set_fecha_nacimiento = (MaterialTextView) findViewById(R.id.muestraFechaNacimiento);
        set_telefono = (MaterialTextView) findViewById(R.id.muestraTelefono);
        set_email = (MaterialTextView) findViewById(R.id.muestraEmail);
        set_descripcion = (MaterialTextView) findViewById(R.id.muestraDescripcion);

        set_nombre.setText(nombreCompleto);
        set_fecha_nacimiento.setText(fechaNacimiento);
        set_telefono.setText(telefono);
        set_email.setText(email);
        set_descripcion.setText(descripcion);
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
