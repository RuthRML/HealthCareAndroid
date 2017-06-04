package es.ipo2.healthcare;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Diego on 3/6/17.
 */

public class EspecialistasSQLiteHelper extends SQLiteOpenHelper {

    String sqlCrearTabla = "CREATE TABLE Especialistas (nombre TEXT, apellidos TEXT, dni TEXT, email TEXT, " +
            "telefono TEXT, especialidad TEXT, sexo TEXT, consulta TEXT, edificio TEXT, operar INTEGER)";

    public EspecialistasSQLiteHelper(Context contexto, String nombreBD, SQLiteDatabase.CursorFactory factory, int versionBD) {
        super(contexto, nombreBD, factory, versionBD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        try {
            db.execSQL("DROP TABLE IF EXISTS Especialistas");
            db.execSQL(sqlCrearTabla);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

}
