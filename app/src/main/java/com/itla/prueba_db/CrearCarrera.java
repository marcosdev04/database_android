package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.entidad.Estudiante;
import com.itla.prueba_db.entidad.Materia;
import com.itla.prueba_db.repositorio.CarreraRepositorio;
import com.itla.prueba_db.repositorio.CarreraRepositorioDblImpl;
import com.itla.prueba_db.repositorio.MateriaRepositorio;
import com.itla.prueba_db.repositorio.MateriaRepositorioDbImpl;

import java.util.ArrayList;
import java.util.List;

public class CrearCarrera extends AppCompatActivity {

    MateriaRepositorio materiaRepositorio;
    CarreraRepositorio carreraRepositorio;

    Toast msj;
    ArrayAdapter<String> adapter;
    Spinner spinnerMateria;
    // list to be set to spinner
    List<String> materias;

    EditText etNombreCarrera;

    Button btnGuardarCarrera;

    public void Limpiar(ConstraintLayout layout){
        for (int i=0; i < layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }

    // SPINNER MATERIAS

    private void loadSpinnerData(Spinner spinner) {
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,mostrarMaterias());
//        spinner.setAdapter(adapter);

    }

        private List<String> mostrarMaterias(){
        materiaRepositorio = new MateriaRepositorioDbImpl(getApplicationContext());
        List<String> nombreMaterias = new ArrayList<>();
        List<Materia> materias = materiaRepositorio.listaMaterias();
        for(Materia materia : materias){
            String NombreMateria = materia.getNombre();
            nombreMaterias.add(NombreMateria);
        }
        return nombreMaterias;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carrera);

        carreraRepositorio = new CarreraRepositorioDblImpl(this);
        btnGuardarCarrera = (Button) findViewById(R.id.btnGuardarCarrera);
        etNombreCarrera = (EditText) findViewById(R.id.etNombreCarrera);

      //loadSpinnerData((Spinner)findViewById(R.id.spinnerMateria));

        materiaRepositorio = new MateriaRepositorioDbImpl(this.getApplicationContext());
        List<Materia> materiaList = materiaRepositorio.listaMaterias();
        for(Materia e: materiaList){
            Log.i("Estudiante", e.getNombre());
        }

        btnGuardarCarrera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Carrera car = new Carrera();
                car.setNombre(etNombreCarrera.getText().toString());

                carreraRepositorio.crear(car);

                Limpiar((ConstraintLayout) findViewById(R.id.constraint));
            }
        });

    }
}
