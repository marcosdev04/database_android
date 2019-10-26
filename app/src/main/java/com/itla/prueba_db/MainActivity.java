package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.CarreraRepositorioDblImpl;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EstudianteRepositorio estudianteRepositorio;
    Toast msj;
    ArrayAdapter<String> adapter;
    Spinner spinnerCarrera;
    // list to be set to spinner
    List<String> carreras;

    public void Limpiar(ConstraintLayout layout){
        for (int i=0; i < layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }

    public void prepareData()
    {
        CarreraRepositorioDblImpl db = new CarreraRepositorioDblImpl(this);
        carreras = db.listaCarrera();
        //adapter for spinner
        adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,carreras);
        //attach adapter to spinner
        spinnerCarrera.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        final EditText edEstudiante = findViewById(R.id.etNombre);
        final EditText edMatricula = findViewById(R.id.etMatricula);

        prepareData();

        Button btnGuardar = (Button)findViewById(R.id.btnGuardar);
//        Button btnMostrar = (Button)findViewById(R.id.btnMostrar);

        /* -- METODO DE GUARDAR -- */
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Estudiante est = new Estudiante();
                est.setNombre(edEstudiante.getText().toString());
                est.setMatricula(edMatricula.getText().toString());

                estudianteRepositorio.crear(est);
                Limpiar((ConstraintLayout) findViewById(R.id.constraint));
            }
        });

        // Imprimir todos los estudiantes registrados
        List<Estudiante> estudianteList = estudianteRepositorio.buscar();
        for(Estudiante e: estudianteList){
            Log.i("Estudiante", e.getNombre());
        }
        Log.i("Estudiante","Done!");


        // MOSTRAR LISTA DE ESTUDIANTES
//        btnMostrar.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), MostrarEstudiante.class);
//                startActivityForResult(intent,0);
//            }
//        });

    }
}
