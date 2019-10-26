package com.itla.prueba_db.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbConnection extends SQLiteOpenHelper {


    private final static int VERSION = 1;
    private final static String NAME_DB = "escuela.db";

    public DbConnection(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TABLA DE ESTUDIANTE
        db.execSQL("CREATE TABLE \"estudiante\" (\n" +"\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" + "\t\"nombre\"\tTEXT NOT NULL,\n" +
                "\t\"matricula\"\tTEXT NOT NULL,\n" + "\t\"idcarrera\"\tINTEGER NOT NULL,\n" + "\tFOREIGN KEY(\"idcarrera\") REFERENCES \"carrera\"(\"idcarrera\")\n" +
                ");");
        // TABLA DE CARRERA
        db.execSQL("CREATE TABLE \"carrera\" (\n" + "\t\"idcarrera\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" + "\t\"nombre\"\tTEXT NOT NULL\n" + ");");
        // TABLA DE MATERIA
        db.execSQL("CREATE TABLE \"materia\" (\n" + "\t\"idmateria\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" + "\t\"nombre\"\tTEXT NOT NULL,\n" + "\t\"creditos\"\tINTEGER NOT NULL\n" + ");");
        // TABLA CARREAR_MATERIA
        db.execSQL("CREATE TABLE \"carrera_materia\" (\n" + "\t\"idcarrera\"\tINTEGER NOT NULL,\n" + "\t\"idmateria\"\tINTEGER NOT NULL,\n" + "\tFOREIGN KEY(\"idmateria\") REFERENCES \"materia\"(\"idmateria\"),\n" +
                "\tFOREIGN KEY(\"idcarrera\") REFERENCES \"carrera\"(\"idcarrera\")\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
