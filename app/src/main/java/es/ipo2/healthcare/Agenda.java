package es.ipo2.healthcare;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Agenda extends AppCompatActivity {

    private ListView lstEspecialistas;
    private TextView lblSeleccionado;
    private ArrayList<Especialista> especialistas;
    AdaptadorLista adaptador;
    private int especialistaSeleccionado;
    private ConectorBD conectorBD;

    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtEspecialidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        especialistas = new ArrayList<Especialista>();

        lstEspecialistas = (ListView)findViewById(R.id.lstEspecialistas);
        adaptador = new AdaptadorLista(this, especialistas);
        lstEspecialistas.setAdapter(adaptador);

        lblSeleccionado = (TextView)findViewById(R.id.lblSeleccionado);
        lstEspecialistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                lblSeleccionado.setText("Especialista Seleccionado: " +
                        ((Especialista)lstEspecialistas.getItemAtPosition(posicion)).getNombre() + " " +
                        ((Especialista) lstEspecialistas.getItemAtPosition(posicion)).getApellidos());
            }
        });

        registerForContextMenu(lstEspecialistas);

        conectorBD = new ConectorBD(this);

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
            case R.id.aniadir:

                Intent i = new Intent(this, DetallesEspecialista.class);
                i.putExtra("modo", "aniadir");
                startActivity(i);

                break;

            case R.id.recargar:
                especialistas.removeAll(especialistas);
                conectorBD.abrir();
                Cursor c = conectorBD.listarEspecialistas();

                if(c.moveToFirst())
                {
                    do {
                        Especialista especialista = new Especialista (null, null, null, null, null, null, "m", null, null, false);
                        especialista.setNombre(c.getString(0));
                        especialista.setApellidos(c.getString(1));
                        especialista.setEmail(c.getString(2));
                        especialista.setTelefono(c.getString(3));
                        especialista.setEspecialidad(c.getString(4));
                        especialista.setSexo(c.getString(5));
                        especialista.setConsulta(c.getString(6));
                        especialista.setEdificio(c.getString(7));
                        if (c.getInt(8) == 0){
                            especialista.setOperar(false);
                        }else{
                            especialista.setOperar(true);
                        }

                        especialistas.add(especialista);

                    } while (c.moveToNext());
                }
                c.close();
                conectorBD.cerrar();
                ((BaseAdapter) lstEspecialistas.getAdapter()).notifyDataSetChanged();
                break;

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
                i.putExtra("modo", "detalles");
                i.putExtra("nombre", especialistas.get(especialistaSeleccionado).getNombre());
                i.putExtra("apellidos", especialistas.get(especialistaSeleccionado).getApellidos());
                i.putExtra("dni", especialistas.get(especialistaSeleccionado).getDni());
                i.putExtra("sexo", especialistas.get(especialistaSeleccionado).getSexo());
                i.putExtra("telefono", especialistas.get(especialistaSeleccionado).getTelefono());
                i.putExtra("email",especialistas.get(especialistaSeleccionado).getEmail());
                i.putExtra("especialidad",especialistas.get(especialistaSeleccionado).getEspecialidad());
                i.putExtra("consulta",especialistas.get(especialistaSeleccionado).getConsulta());
                i.putExtra("edificio",especialistas.get(especialistaSeleccionado).getEdificio());
                i.putExtra("operar",especialistas.get(especialistaSeleccionado).isOperar());

                startActivity(i);
                break;

            case R.id.eliminarEspecialista:
                conectorBD.eliminarEspecialista(especialistas.get(especialistaSeleccionado).getDni());
                break;
        }
        return true;
    }

}
