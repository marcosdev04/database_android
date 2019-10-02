package com.itla.prueba_db.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itla.prueba_db.entidad.Estudiante;

import java.util.List;

public class EstudianteRepositorioDbImpl implements EstudianteRepositorio{

    private DbConnection dbConnection;

    public EstudianteRepositorioDbImpl(Context context){
        this.dbConnection = new DbConnection(context);
    }

    @Override
    public void crear(Estudiante estudiante) {

        ContentValues cv = new ContentValues();

        cv.put("nombre", estudiante.getNombre());
        cv.put("matricula", estudiante.getMatricula());

        SQLiteDatabase db =  dbConnection.getWritableDatabase();
        long id = db.insert("estudiante",null,cv);

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
    public List<Estudiante> buscar() {
        return null;
    }
}
