package es.ipo2.healthcare;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Agenda extends AppCompatActivity {

    private String[] especialistasEstatico={"María García","José Pérez","José Ruiz", "Carmen López"};
    private ListView lstEspecialistas;
    private TextView lblSeleccionado;
    private ArrayList<Especialista> especialistas;
    private int especialistaSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        especialistas = new ArrayList<Especialista>();

        especialistas.add(new Especialista("María", "Rodríguez1", "mariarodriguez@correo.com", "926587896", "Cardiologíaaa", 'h', "Consulta 2", "Edificio 1", true));
        especialistas.add(new Especialista("María", "Rodríguez1", "mariarodriguez@correo.com", "926587896", "Cardiologíaaa", 'h', "Consulta 2", "Edificio 1", true));
        especialistas.add(new Especialista("María", "Rodríguez1", "mariarodriguez@correo.com", "926587896", "Cardiologíaaa", 'h', "Consulta 2", "Edificio 1", false));
        especialistas.add(new Especialista("María", "Rodríguez1", "mariarodriguez@correo.com", "926587896", "Cardiologíaaa", 'h', "Consulta 2", "Edificio 1", true));

        lstEspecialistas =(ListView)findViewById(R.id.lstEspecialistas);
        AdaptadorLista adaptador = new AdaptadorLista(this, especialistas);
        lstEspecialistas.setAdapter(adaptador);

        lblSeleccionado = (TextView)findViewById(R.id.lblSeleccionado);
        lstEspecialistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                lblSeleccionado.setText("Contacto seleccionado: "+ ((Especialista)lstEspecialistas.getItemAtPosition(posicion)).getNombre());
            }
        });

        registerForContextMenu(lstEspecialistas);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agenda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.acercaDe:
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setTitle("Acerca de...");
                builder.setMessage("Aplicación creada por:\n\t Diego Andérica Richard\n\t Ruth Rodríguez-Manzaneque López ");
                builder.setPositiveButton("OK",null);
                builder.create();
                builder.show();
                break;
        }
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        especialistaSeleccionado= info.position;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_contextual, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.verDetalles:
                Intent i = new Intent(this, DetallesEspecialista.class);
                i.putExtra("nombre", especialistas.get(especialistaSeleccionado).getNombre());
                i.putExtra("apellidos", especialistas.get(especialistaSeleccionado).getApellidos());
                i.putExtra("telefono", especialistas.get(especialistaSeleccionado).getTelefono());
                i.putExtra("email",especialistas.get(especialistaSeleccionado).getEmail());
                i.putExtra("especialidad",especialistas.get(especialistaSeleccionado).getEspecialidad());
                i.putExtra("consulta",especialistas.get(especialistaSeleccionado).getConsulta());
                i.putExtra("edificio",especialistas.get(especialistaSeleccionado).getEdificio());
                i.putExtra("operar",especialistas.get(especialistaSeleccionado).isOperar());

                //startActivity(i);
                startActivityForResult(i, 1234);
                break;
        }
        return true;
    }

}
