package es.ipo2.healthcare;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Diego on 3/6/17.
 */

public class ConectorBD {

    static final String NOMBRE_BD = "AgendaLocal";
    private EspecialistasSQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public ConectorBD (Context ctx)
    {
        dbHelper = new EspecialistasSQLiteHelper(ctx, NOMBRE_BD, null, 1);
    }

    public ConectorBD abrir() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        if (db != null){
            db.close();
        }
    }

    public void insertarEspecialista(String nombre, String apellidos, String dni, String email, String telefono, String especialidad, String sexo, String consulta, String edificio, int operar)
    {
        String consultaSQL = "INSERT INTO Especialistas VALUES('"+nombre+"', '"+apellidos+"', '"+dni+"', '"+email+"', '"+telefono+"', '"+especialidad+"', '"+sexo+"', '"+consulta+"', '"+edificio+"', '"+operar+"')";
        db.execSQL(consultaSQL);
    }

    public Cursor listarEspecialistas()
    {
        return db.rawQuery("SELECT * FROM Especialistas", null);
    }

    public void eliminarEspecialista (String dni){
        db.delete("Especialistas", "dni" + "=" + dni, null);

    }

}
