package es.ipo2.healthcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;

public class DetallesEspecialista extends AppCompatActivity {

    private EditText txtNombreC;
    private EditText txtApellidosC;
    private EditText txtEmailC;
    private EditText txtTelefonoC;
    private EditText txtEspecialidadC;
    private EditText txtConsultaC;
    private EditText txtEdificioC;
    private CheckBox checkOperarC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_especialista);

        txtNombreC = (EditText)findViewById(R.id.txtNombre);
        txtApellidosC = (EditText)findViewById(R.id.txtApellidos);
        txtEmailC = (EditText)findViewById(R.id.txtEmail);
        txtTelefonoC = (EditText)findViewById(R.id.txtTelefono);
        txtEspecialidadC = (EditText)findViewById(R.id.txtEspecialidad);
        txtConsultaC = (EditText)findViewById(R.id.txtConsulta);
        txtEdificioC = (EditText)findViewById(R.id.txtEdificio);
        checkOperarC = (CheckBox) findViewById(R.id.checkOperar);

        Bundle bundle = getIntent().getExtras();
        txtNombreC.setText(bundle.getString("nombre"));
        txtApellidosC.setText(bundle.getString("apellidos"));
        txtEmailC.setText(bundle.getString("email"));
        txtTelefonoC.setText(bundle.getString("telefono"));
        txtEspecialidadC.setText(bundle.getString("especialidad"));
        txtConsultaC.setText(bundle.getString("consulta"));
        txtEdificioC.setText(bundle.getString("edificio"));
        checkOperarC.setChecked(bundle.getBoolean("operar"));

    }
}
