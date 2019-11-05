package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.repositorio.CarreraRepositorio;
import com.itla.prueba_db.repositorio.CarreraRepositorioDblImpl;
import com.itla.prueba_db.repositorio.EstudianteRepositorio;
import com.itla.prueba_db.repositorio.EstudianteRepositorioDbImpl;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewCarreras;

    EstudianteRepositorio estudianteRepositorio;
    CarreraRepositorio carreraRepositorio;
    List<Carrera> carreras;
    Carrera carrera,mtposition;
    ArrayAdapter<String> adapter;
    Integer idcarrera;

    Toast msj;

    EditText edEstudiante, edMatricula;
    Button btnGuardar,btnAddCarrera,btnCancelar;

    public void Limpiar(ConstraintLayout layout){
        for (int i=0; i < layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }

    private void setMensaje(Toast msj, String mensaje){
        msj = Toast.makeText(getApplicationContext(),mensaje , Toast.LENGTH_SHORT);
        msj.setGravity(Gravity.CENTER, 0, 0);
        msj.show();

    }


    public void prepareData(Spinner spi)
    {
       carreraRepositorio = new CarreraRepositorioDblImpl(this.getApplicationContext());

       Carrera carrera = new Carrera(0,"Seleccione carrera");

       carreras = carreraRepositorio.listaCarrera();

       carreras.add(carrera);

        ArrayAdapter<Carrera> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item, carreras);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spi.setAdapter(adapter);
        spi.setSelection(carreras.size() - 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        estudianteRepositorio = new EstudianteRepositorioDbImpl(this.getBaseContext());

        edEstudiante = findViewById(R.id.etNombre);
        edMatricula = findViewById(R.id.etMatricula);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnAddCarrera = (Button) findViewById(R.id.btnAddCarrera);
        btnCancelar = (Button) findViewById(R.id.btnCancelarEstudiante);

        Spinner spinnerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);

        prepareData(spinnerCarrera);


       spinnerCarrera.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               carrera = (Carrera) parent.getItemAtPosition(position);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });


        /* -- METODO DE GUARDAR -- */
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Estudiante est = new Estudiante();
//                est.setNombre(edEstudiante.getText().toString());
//                est.setMatricula(edMatricula.getText().toString());
//                est.setIdcarrera(carrera);
                mtposition = (Carrera) ((Spinner)findViewById(R.id.spinnerCarrera)).getSelectedItem();
                idcarrera = carrera.getId();

                if(idcarrera < 0){
                    setMensaje(msj,"Debe selecionar una carrera");
                }else if(edMatricula.getText().toString().isEmpty()){
                    setMensaje(msj,"Debe ingresar la matricula");
                }else if(edEstudiante.getText().toString().isEmpty()){
                    setMensaje(msj,"Debe ingresar el nombre del estudiante");
                }else{
                    Estudiante estudiante = new Estudiante();
                    estudiante.setNombre(edEstudiante.getText().toString());
                    estudiante.setMatricula(edMatricula.getText().toString());
                    estudiante.setIdcarrera(idcarrera);
                    estudianteRepositorio.crear(estudiante);
                }

                Limpiar((ConstraintLayout) findViewById(R.id.constraint));
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* -- METODO PARA AGREGAR CARRERA -- */
        btnAddCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carrera == null || carrera.getId() == 0){
                    Intent intent = new Intent(v.getContext(), CrearCarrera.class);
                    startActivityForResult(intent,0);
                }else{
                   return;
                }
            }
        });

        // Imprimir todos los estudiantes registrados
//        List<Estudiante> estudianteList = estudianteRepositorio.buscar();
//        for(Estudiante e: estudianteList){
//            Log.i("Estudiante", e.getNombre());
//        }
//        Log.i("Estudiante","Done!");


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

    @Override
    protected void onStart() {
        super.onStart();
        Spinner spinnerCarrera = (Spinner) findViewById(R.id.spinnerCarrera);
        prepareData(spinnerCarrera);
    }
}
