package com.itla.prueba_db.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itla.prueba_db.entidad.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteRepositorioDbImpl implements EstudianteRepositorio{

    private DbConnection dbConnection;
    private static final String TABLE = "estudiante";

    public EstudianteRepositorioDbImpl(Context context){
        this.dbConnection = new DbConnection(context);
    }

    @Override
    public void crear(Estudiante estudiante) {

        ContentValues cv = new ContentValues();

        cv.put("nombre", estudiante.getNombre());
        cv.put("matricula", estudiante.getMatricula());
        cv.put("idcarrera", estudiante.getIdcarrera());

        SQLiteDatabase db =  dbConnection.getWritableDatabase();
        long id = db.insert(TABLE,null,cv);

        if(id <= 0){
            Log.i("EstudianteRepositorio","Ocurrio un error al crear estudiante!");
        }else{
            Log.i("EstudianteRepositorio","El estudiante se ha creado exitoso id = "+id);
        }
    }

    @Override
    public void actualizar(Estudiante estudiante) {

    }

    @Override
    public void eliminar(Estudiante estudiante) {

    }

    @Override
    public Estudiante buscar(int id) {
        return null;
    }

    @Override
    public List<Estudiante> buscar()
    {
        List<Estudiante> estudiantes = new ArrayList<>();

        SQLiteDatabase db = dbConnection.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT e.matricula as matricula, e.nombre as nombre, c.nombre as nombrecarrera\n" +
                "FROM estudiante e INNER JOIN carrera c\n" +
                "ON e.idcarrera = c.idcarrera",null);

        Estudiante est;

        while (c.moveToNext()){
            // c.getColumnIndex("id") para obtener el indice del campo
            String matricula = c.getString(c.getColumnIndex("matricula"));
            String nombre = c.getString(c.getColumnIndex("nombre"));
            String nombrecarrera = c.getString(c.getColumnIndex("nombrecarrera"));

            est = new Estudiante();
            est.setMatricula(matricula);
            est.setNombre(nombre);
            est.setNombreCarrera(nombrecarrera);
            estudiantes.add(est);
        }
        c.close();

        return estudiantes;
    }
}
