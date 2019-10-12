package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;
    Toast msj;

//    private void Limpiar(List<EditText> text){
//        for ( EditText ed  text) {
//            ed.
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        final EditText edEstudiante = findViewById(R.id.etNombre);
        EditText edMatricula = findViewById(R.id.etMatricula);

        Button btnGuardar = (Button)findViewById(R.id.btnGuardar);
        Button btnMostrar = (Button)findViewById(R.id.btnMostrar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estudiante est = new Estudiante();
                est.setNombre(edEstudiante.getText().toString());
                est.setMatricula(edEstudiante.getText().toString());

                estudianteRepositorio.crear(est);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MostrarEstudiante.class);
                startActivityForResult(intent,0);
            }
        });


    }
}
