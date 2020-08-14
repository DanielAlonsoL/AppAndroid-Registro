package com.danielalonso.activities;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.datepicker.DateSelector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText nombreCompleto;
    EditText fechaNacimiento;
    EditText telefono;
    EditText email;
    EditText descripcion;

    Button siguiente;

    ImageButton obtenerFecha;
    ListView miLista;
    SwipeRefreshLayout limpiando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desplegarPicker();
        limpiarContenido();
        //presionarBoton();
    }
/*
    public void presionarBoton(){
        siguiente = (Button) findViewById(R.id.btnSiguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });
    }

    public void guardarDatos(){
        Intent intent = new Intent(MainActivity.this, Confirmar.class);

        intent.putExtra("nombreCompleto", nombreCompleto.getText().toString());
        intent.putExtra("telefono",telefono.getText().toString());
        intent.putExtra("fechaNacimiento", fechaNacimiento.getText().toString());
        intent.putExtra("email",email.getText().toString());
        intent.putExtra("descripcion",descripcion.getText().toString());

        startActivity(intent);
    }

*/
    public void limpiarContenido(){
        limpiando = findViewById(R.id.swipeRefresh);
        miLista = findViewById(R.id.list);

        limpiando.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                limpiar();
            }
        });
    }

    public void limpiar(){
        fechaNacimiento = findViewById(R.id.fechaNacimiento);
        nombreCompleto = findViewById(R.id.nombreCompleto);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);
        descripcion = findViewById(R.id.descripcion);

        miLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1));

        nombreCompleto.setText("");
        fechaNacimiento.setText("");
        telefono.setText("");
        email.setText("");
        descripcion.setText("");

        limpiando.setRefreshing(false);
    }

    public void desplegarPicker(){

        fechaNacimiento = (EditText) findViewById(R.id.fechaNacimiento);
        obtenerFecha = (ImageButton) findViewById(R.id.ib_obtener_fecha);

        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                final DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        fechaNacimiento.setText(mDay+"/"+(mMonth+1)+"/"+mYear);
                    }
                }, day, month, year);

                dpd.show();

                obtenerFecha.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                     dpd.show();
                    }
                });


            }
        });
    }
}