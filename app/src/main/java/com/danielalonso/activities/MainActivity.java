package com.danielalonso.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextInputEditText etNombre;
    private TextInputEditText etApellidos;
    private TextInputEditText etNumCuenta;
    private TextInputEditText etFechaNacimiento;

    private MaterialButton enviar;
    private ListView miLista;
    private SwipeRefreshLayout limpiando;

    private Resources res;

    private MaterialAutoCompleteTextView ac_carreras;

    private ArrayAdapter<String> adapter;

    private String[] carreras;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ac_carreras = (MaterialAutoCompleteTextView) findViewById(R.id.spCarreras);

        res = getResources();
        carreras = res.getStringArray(R.array.carreras);

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, carreras);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ac_carreras.setAdapter(adapter);

        desplegarPicker();
        limpiarContenido();

        intent = new Intent(MainActivity.this, Confirmar.class);

        ac_carreras.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String catchCarrera = carreras[i];
                intent.putExtra(getString(R.string.p_carrera), catchCarrera);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ac_carreras.setError(getString(R.string.error_carreras));
                ac_carreras.requestFocus();
            }
        });

        enviar = (MaterialButton) findViewById(R.id.btnEnviar);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etNombre = (TextInputEditText) findViewById(R.id.etNombre);
                etApellidos = (TextInputEditText) findViewById(R.id.etApellidos);
                etNumCuenta = (TextInputEditText) findViewById(R.id.etNum_cuenta);
                etFechaNacimiento = (TextInputEditText) findViewById(R.id.etFechaNacimiento);


                String catchNombre = etNombre.getText().toString();
                String catchApellidos = etApellidos.getText().toString();
                String catchNumCuenta = etNumCuenta.getText().toString();
                String catchFecha = etFechaNacimiento.getText().toString();

                if (validaCampos(etNombre, etApellidos, etNumCuenta, etFechaNacimiento)) {

                    intent.putExtra(getString(R.string.p_nombre), catchNombre);
                    intent.putExtra(getString(R.string.p_apellidos), catchApellidos);
                    intent.putExtra(getString(R.string.p_num_cuenta), catchNumCuenta);
                    intent.putExtra(getString(R.string.p_fecha_nacimiento), catchFecha);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.texto_toast), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean validaCampos(TextInputEditText etNombre, TextInputEditText etApellidos, TextInputEditText etNumCuenta,
                                TextInputEditText etFechaNacimiento) {

        if (etNombre.getText().toString().equals("")) {
            etNombre.setError(getString(R.string.error_nombre));
            etNombre.requestFocus();
            return false;
        }

        if (etApellidos.getText().toString().equals("")) {
            etApellidos.setError(getString(R.string.error_apellidos));
            etApellidos.requestFocus();
            return false;
        }

        if (etNumCuenta.getText().toString().equals("")) {
            etNumCuenta.setError(getString(R.string.error_num_cuenta));
            etNumCuenta.requestFocus();
            return false;
        }

        if (etFechaNacimiento.getText().toString().equals("")) {
            etFechaNacimiento.setError(getString(R.string.error_fecha));
            etFechaNacimiento.requestFocus();
            return false;
        }


        return true;
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
        etNombre = (TextInputEditText) findViewById(R.id.etNombre);
        etApellidos = (TextInputEditText) findViewById(R.id.etApellidos);
        etFechaNacimiento = (TextInputEditText) findViewById(R.id.etFechaNacimiento);

        miLista.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1));

        etNombre.setText("");
        etFechaNacimiento.setText("");
        etApellidos.setText("");

        limpiando.setRefreshing(false);
    }

    public void desplegarPicker(){

        etFechaNacimiento = (TextInputEditText) findViewById(R.id.etFechaNacimiento);

        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                final DatePickerDialog dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                        etFechaNacimiento.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);

                dpd.show();

            }
        });
    }
}