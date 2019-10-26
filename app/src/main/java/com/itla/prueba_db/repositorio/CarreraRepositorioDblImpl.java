package com.itla.prueba_db.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itla.prueba_db.entidad.Carrera;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositorioDblImpl implements CarreraRepositorio {

    private DbConnection dbConnection;
    private static final String TABLE = "carrera";

    public CarreraRepositorioDblImpl(Context context){
        this.dbConnection = new DbConnection(context);
    }

    // METODO PARA CREAR CARRERA
    @Override
    public void crear(Carrera carrera) {

        ContentValues cv = new ContentValues();

        cv.put("nombre",carrera.getNombre());
        SQLiteDatabase db = dbConnection.getWritableDatabase();

        long id = db.insert(TABLE,null,cv);

        if(id <= 0){
            Log.i("CarreraRepositorio","Ocurrio un error al crear la carrera!");
        }else{
            Log.i("CarreraRepositorio","La carrera se ha creado con exitoso id = "+id);
        }
    }

    @Override
    public void actualizar(Carrera carreras) {

    }

    @Override
    public void eliminar(Carrera carrera) {

    }

    @Override
    public Carrera buscar(int id) {
        return null;
    }

    @Override
    public List<Carrera> CarreraDetallada() {
        List<Carrera> carreras = new ArrayList<>();

        SQLiteDatabase db = dbConnection.getReadableDatabase();

        Cursor cursor = db.query(TABLE + "INNER JOIN carrera_materia cm ON c.idcarrera = cm.idcarrera INNER JOIN materia m ON m.idmateria = cm.idmateria",
                new String[] {"c.nombre","count(m.idmateria) as cantidad_materia", "sum(m.creditos) AS creditos"},null,null,
                "c.idcarrera",null,null,null);
        Carrera carr;
        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String carrera = cursor.getString(cursor.getColumnIndex("nombre"));
            Integer cant_materia = cursor.getInt(cursor.getColumnIndex("cantidad_materia"));
            Integer creditos = cursor.getInt(cursor.getColumnIndex("creditos"));

            carr = new Carrera();
            carr.setId(id);
            carr.setNombre(carrera);
            carr.setCantidad_materia(cant_materia);
            carr.setCreditos(creditos);
            carreras.add(carr);
        }
        cursor.close();
        db.close();
        return carreras;
    }

    @Override
    public List<String> listaCarrera() {
        List<String> carreras = new ArrayList<>();

        SQLiteDatabase db = dbConnection.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id,nombre FROM carrera",null);
        Carrera carr;
        while (cursor.moveToNext()){
//            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
//            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            carreras.add(cursor.getString(0));
            carreras.add(cursor.getString(1));
        }

        cursor.close();
        db.close();
        return carreras;
    }
}
