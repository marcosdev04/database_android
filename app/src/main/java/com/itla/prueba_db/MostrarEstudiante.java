package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import java.util.ArrayList;
import java.util.List;


public class MostrarEstudiante extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_estudiante);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(getBaseContext());

        List<Estudiante> listaEstudiantes = estudianteRepositorio.buscar();

        for(Estudiante e : listaEstudiantes){
            Log.i("MostrarEstudiante" , e.getMatricula());
        }

        RecyclerView recyclerViewEstudiante = (RecyclerView) findViewById(R.id.recyclerViewEstudiantes);
        recyclerViewEstudiante.setLayoutManager(new LinearLayoutManager(this));


        AdaptadorEstudiante adapter = new AdaptadorEstudiante(listaEstudiantes);
        recyclerViewEstudiante.setAdapter(adapter);
    }
}
