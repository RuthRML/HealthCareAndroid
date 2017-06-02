package ipo2.es.healthcare;

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
        TextView lblEspecialidad = (TextView)item.findViewById(R.id.lblEespecialidad);
        lblEspecialidad.setText(especialistas.get(position).getEspecialidad());
        ImageView imagContacto = (ImageView)item.findViewById(R.id.imageEspecialista);
        return(item);
    }
}
