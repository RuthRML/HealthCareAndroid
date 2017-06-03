package es.ipo2.healthcare;

/**
 * Created by Diego on 3/6/17.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ruth on 02/06/2017.
 */

public class AdaptadorLista extends ArrayAdapter {
    Activity context;

    private ArrayList<Especialista> especialistas;

    AdaptadorLista (Activity context, ArrayList<Especialista> especialistas) {
        super(context, R.layout.layout_item, especialistas);
        this.context = context;
        this.especialistas = especialistas;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.layout_item, null);

        TextView lblNombre = (TextView)item.findViewById(R.id.lblNombre);
        lblNombre.setText(especialistas.get(position).getNombre());

        TextView lblApellidos = (TextView)item.findViewById(R.id.lblApellidos);
        lblApellidos.setText(especialistas.get(position).getApellidos());

        TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEspecialidad);
        lblEspecialidad.setText(especialistas.get(position).getEspecialidad());

        ImageView imageEspecialista = (ImageView)item.findViewById(R.id.imageEspecialista);

        switch (especialistas.get(position).getSexo()){
            case 'h':
                imageEspecialista.setImageResource(R.drawable.doctor);
                break;
            case 'm':
                imageEspecialista.setImageResource(R.drawable.doctora);
        }

        return(item);
    }
}
