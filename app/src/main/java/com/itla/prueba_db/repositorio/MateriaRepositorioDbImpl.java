package com.itla.prueba_db.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itla.prueba_db.entidad.Materia;

import java.util.ArrayList;
import java.util.List;

public class MateriaRepositorioDbImpl implements MateriaRepositorio {

    private DbConnection dbConnection;
    private static final String TABLE = "materia";

    public MateriaRepositorioDbImpl(Context context){
        this.dbConnection = new DbConnection(context);
    }

    // Metodo para crear Materia
    @Override
    public void crear(Materia materia) {

        ContentValues cv = new ContentValues();

        cv.put("nombre",materia.getNombre());
        cv.put("creditos",materia.getCreditos());
        SQLiteDatabase db = dbConnection.getWritableDatabase();

        long id = db.insert(TABLE,null,cv);

        if(id <= 0){
            Log.i("MateriaRepositorio","Ocurrio un error al crear la materia!");
        }else{
            Log.i("MateriaRepositorio","La materia se ha creado con exitoso id = "+id);
        }
    }

    @Override
    public void actualizar(Materia materias) {

    }

    @Override
    public void eliminar(Materia materia) {

    }

    @Override
    public Materia buscar(int id) {
        return null;
    }

    public List<Materia> listaMaterias(){

        List<Materia> materias = new ArrayList<>();

        SQLiteDatabase db = dbConnection.getReadableDatabase();

        Cursor c = db.query(TABLE, null,null,null,null,null, null);

        while (c.moveToNext()){
            Materia mat = new Materia(0,"",0);

            mat = new Materia(0,"",0);
            mat.setId(c.getInt(c.getColumnIndex("idmateria")));
            mat.setNombre(c.getString(c.getColumnIndex("nombre")));
            mat.setCreditos(c.getInt(c.getColumnIndex("creditos")));
            materias.add(mat);
        }
        c.close();
        db.close();
        return materias;
    }
}
