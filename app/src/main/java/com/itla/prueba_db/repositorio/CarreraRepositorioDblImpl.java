package com.itla.prueba_db.repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.entidad.Materia;

import java.util.ArrayList;
import java.util.List;

public class CarreraRepositorioDblImpl implements CarreraRepositorio {

    private DbConnection dbConnection;
    private static final String TABLE = "carrera";
    private static final String TABLE_MATERIA_CARRERA = "carrera_materia";

    public CarreraRepositorioDblImpl(Context context){
        this.dbConnection = new DbConnection(context);
    }

    // METODO PARA CREAR CARRERA
    @Override
    public int crear(Carrera carrera) {

        ContentValues cv = new ContentValues();

        cv.put("nombre",carrera.getNombre());
        SQLiteDatabase db = dbConnection.getWritableDatabase();

        long id = db.insert(TABLE,null,cv);

        if(id > 0){
            return (int) id;
        }else{
            return 0;
        }
    }

    @Override
    public int materia_carrera(int idcarrera, List<Integer> idmateria) {
        ContentValues cv = new ContentValues();

        SQLiteDatabase db = dbConnection.getReadableDatabase();

        long id = 0;

        for(int i=0; i < idmateria.size();i++){
            cv.put("idcarrera",idcarrera);
            cv.put("idmateria", idmateria.get(i));
            id = db.insert(TABLE_MATERIA_CARRERA,null,cv);
        }

        if(id!=0)
            return 1;
        else
            return 0;
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

        //Cursor cursor = db.query(TABLE + "INNER JOIN carrera_materia cm ON c.idcarrera = cm.idcarrera INNER JOIN materia m ON m.idmateria = cm.idmateria",
               // new String[] {"c.nombre","count(m.idmateria) as cantidad_materia", "sum(m.creditos) AS creditos"},null,null,
                //"c.idcarrera",null,null,null);

        Cursor cursor = db.rawQuery("SELECT c.nombre as nombre, count(m.idmateria) AS cantidad_materia,\n" + "\t sum(m.creditos) AS creditos\n" +
                "FROM carrera c  INNER JOIN carrera_materia cm ON c.idcarrera = cm.idcarrera\n" +
                "\tINNER JOIN materia m ON m.idmateria = cm.idmateria\n" +
                "GROUP BY c.idcarrera",null);

        Carrera carr;

        while (cursor.moveToNext()){

            String carrera = cursor.getString(cursor.getColumnIndex("nombre"));
            Integer cant_materia = cursor.getInt(cursor.getColumnIndex("cantidad_materia"));
            Integer creditos = cursor.getInt(cursor.getColumnIndex("creditos"));

            carr = new Carrera(0,"");
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
    public List<Carrera> listaCarrera() {

        List<Carrera> carreras2 = new ArrayList<>();

        SQLiteDatabase db = dbConnection.getReadableDatabase();

        Cursor c = db.query(TABLE, null,null,null,null,null, null);

        while (c.moveToNext()){

            Carrera carr = new Carrera(0,"");

            carr = new Carrera(0,"");
            carr.setId(c.getInt(c.getColumnIndex("idcarrera")));
            carr.setNombre(c.getString(c.getColumnIndex("nombre")));
            carreras2.add(carr);
        }

        c.close();
        db.close();
        return carreras2;
    }
}
