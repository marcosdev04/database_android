package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

public class MainActivity extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        Estudiante est1 = new Estudiante();
        est1.setNombre("Marcos");
        est1.setMatricula("1-12-1803");
        estudianteRepositorio.crear(est1);

        Estudiante est2 = new Estudiante();
        est2.setNombre("Toreto");
        est2.setMatricula("2-14-1904");
        estudianteRepositorio.crear(est2);

    }
}
