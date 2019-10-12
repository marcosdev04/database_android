package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class MostrarEstudiante extends AppCompatActivity {
    EstudianteRepositorio estudianteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_estudiante);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(getBaseContext());

        List<Estudiante> estudiantes = estudianteRepositorio.buscar();
        ListView lv = findViewById(R.id.listEstudiante);
        // adapter para presentar los elementos
        lv.setAdapter(new ArrayAdapter<>(getBaseContext(),R.layout.support_simple_spinner_dropdown_item,estudiantes.toArray()));


    }
}
