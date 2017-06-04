package es.ipo2.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DetallesEspecialista extends AppCompatActivity {

    private EditText txtNombreC;
    private EditText txtApellidosC;
    private EditText txtDniC;
    private Spinner spinnerSexoC;
    private EditText txtEmailC;
    private EditText txtTelefonoC;
    private EditText txtEspecialidadC;
    private EditText txtConsultaC;
    private EditText txtEdificioC;
    private CheckBox checkOperarC;
    private Button btnMultiusoC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_especialista);

        txtNombreC = (EditText)findViewById(R.id.txtNombre);
        txtApellidosC = (EditText)findViewById(R.id.txtApellidos);
        txtDniC = (EditText)findViewById(R.id.txtDni);
        spinnerSexoC = (Spinner)findViewById(R.id.spinnerSexo);
        txtEmailC = (EditText)findViewById(R.id.txtEmail);
        txtTelefonoC = (EditText)findViewById(R.id.txtTelefono);
        txtEspecialidadC = (EditText)findViewById(R.id.txtEspecialidad);
        txtConsultaC = (EditText)findViewById(R.id.txtConsulta);
        txtEdificioC = (EditText)findViewById(R.id.txtEdificio);
        checkOperarC = (CheckBox)findViewById(R.id.checkOperar);
        btnMultiusoC = (Button)findViewById(R.id.btnMultiuso);

        Bundle bundle = getIntent().getExtras();

        if (bundle.getString("modo").equalsIgnoreCase("aniadir")){
            habilitarComponentes(true);

            btnMultiusoC.setText("Guardar Nuevo Contacto");
            btnMultiusoC.setVisibility(View.VISIBLE);


        }else if (bundle.getString("modo").equalsIgnoreCase("detalles")) {

            habilitarComponentes (false);

            txtNombreC.setText(bundle.getString("nombre"));
            txtApellidosC.setText(bundle.getString("apellidos"));
            txtDniC.setText(bundle.getString("dni"));
            if (bundle.getString("sexo").equalsIgnoreCase("h")) {
                spinnerSexoC.setSelection(0);
            } else {
                spinnerSexoC.setSelection(1);
            }

            txtTelefonoC.setText(bundle.getString("telefono"));
            txtEmailC.setText(bundle.getString("email"));
            txtEspecialidadC.setText(bundle.getString("especialidad"));
            txtConsultaC.setText(bundle.getString("consulta"));
            txtEdificioC.setText(bundle.getString("edificio"));
            checkOperarC.setChecked(bundle.getBoolean("operar"));
        }

        btnMultiusoC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (btnMultiusoC.getText().toString().equalsIgnoreCase("guardar nuevo contacto")){
                    ConectorBD conectorBD = new ConectorBD(getApplicationContext());

                    conectorBD.abrir();

                    Toast.makeText(getBaseContext(), "Se ha añadido un nuevo especialista. Pulsar recargar para ver los cambios" , Toast.LENGTH_LONG).show();

                    if (checkOperarC.isChecked() == false){
                        conectorBD.insertarEspecialista(txtNombreC.getText().toString(),
                                txtApellidosC.getText().toString(), txtDniC.getText().toString(),
                                txtEmailC.getText().toString(), txtTelefonoC.getText().toString(),
                                txtEspecialidadC.getText().toString(),
                                spinnerSexoC.getSelectedItem().toString().charAt(0) + "",
                                txtConsultaC.getText().toString(),
                                txtEdificioC.getText().toString(), 0);
                    }else{
                        conectorBD.insertarEspecialista(txtNombreC.getText().toString(),
                                txtApellidosC.getText().toString(), txtDniC.getText().toString(),
                                txtEmailC.getText().toString(), txtTelefonoC.getText().toString(),
                                txtEspecialidadC.getText().toString(),
                                spinnerSexoC.getSelectedItem().toString().charAt(0) + "",
                                txtConsultaC.getText().toString(),
                                txtEdificioC.getText().toString(), 1);
                    }

                    conectorBD.cerrar();

                    finish();

                }
            }
        });


    }

    private void habilitarComponentes(boolean b) {
        txtNombreC.setEnabled(b);
        txtApellidosC.setEnabled(b);
        txtDniC.setEnabled(b);
        spinnerSexoC.setEnabled(b);
        txtEmailC.setEnabled(b);
        txtTelefonoC.setEnabled(b);
        txtEspecialidadC.setEnabled(b);
        txtConsultaC.setEnabled(b);
        txtEdificioC.setEnabled(b);
        checkOperarC.setEnabled(b);
    }
}
