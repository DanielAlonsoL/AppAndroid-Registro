package com.danielalonso.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextInputEditText nombreCompleto;
    private TextInputEditText fechaNacimiento;
    private TextInputEditText telefono;
    private TextInputEditText email;
    private TextInputEditText descripcion;

    private MaterialButton siguiente;
    private ListView miLista;
    private SwipeRefreshLayout limpiando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desplegarPicker();
        limpiarContenido();

        siguiente = (MaterialButton) findViewById(R.id.btnSiguiente);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombreCompleto = (TextInputEditText) findViewById(R.id.nombreCompleto);
                fechaNacimiento = (TextInputEditText) findViewById(R.id.fechaNacimiento);
                telefono = (TextInputEditText) findViewById(R.id.telefono);
                email = (TextInputEditText) findViewById(R.id.email);
                descripcion = (TextInputEditText) findViewById(R.id.descripcion);

                String catchNombre = (nombreCompleto.getText().toString());
                String catchTelefono = (telefono.getText().toString());
                String catchFecha = (fechaNacimiento.getText().toString());
                String catchEmail = (email.getText().toString());
                String catchDescripcion = (descripcion.getText().toString());

                if (catchNombre != null && catchTelefono != null && catchFecha != null && catchEmail != null && catchDescripcion != null) {
                    Intent intent = new Intent(MainActivity.this, Confirmar.class);

                    intent.putExtra(getString(R.string.p_nombre), catchNombre);
                    intent.putExtra(getString(R.string.p_fecha), catchFecha);
                    intent.putExtra(getString(R.string.p_telefono), catchTelefono);
                    intent.putExtra(getString(R.string.p_email), catchEmail);
                    intent.putExtra(getString(R.string.p_descripcion), catchDescripcion);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.texto_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void limpiarContenido() {
        limpiando = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        miLista = (ListView) findViewById(R.id.list);

        limpiando.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                limpiar();
            }
        });
    }

    public void limpiar() {
        fechaNacimiento = (TextInputEditText) findViewById(R.id.fechaNacimiento);
        nombreCompleto = (TextInputEditText) findViewById(R.id.nombreCompleto);
        telefono = (TextInputEditText) findViewById(R.id.telefono);
        email = (TextInputEditText) findViewById(R.id.email);
        descripcion = (TextInputEditText) findViewById(R.id.descripcion);

        miLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1));

        nombreCompleto.setText("");
        fechaNacimiento.setText("");
        telefono.setText("");
        email.setText("");
        descripcion.setText("");

        limpiando.setRefreshing(false);
    }

    public void desplegarPicker(){

        fechaNacimiento = (TextInputEditText) findViewById(R.id.fechaNacimiento);

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

            }
        });
    }
}