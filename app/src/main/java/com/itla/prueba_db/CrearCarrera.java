package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Carrera;
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
    List<Materia> materias;
    Materia materia;
    Materia mtposition;
    List<Integer> idmateri;
    Toast mensaje;

    EditText etNombreCarrera;
    Button btnGuardarCarrera,btnAdd,btnCancelar;


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

    // SPINNER MATERIAS
    private void loadSpinnerData(Spinner spinner) {

        materiaRepositorio = new MateriaRepositorioDbImpl(getApplicationContext());

        Materia mt1 = new Materia(0,"Seleccione una materia",0);

        materias = materiaRepositorio.listaMaterias();
        materias.add(mt1);

        ArrayAdapter<Materia> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_item,materias);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(materias.size()-1);
    }

    RecyclerView recyclerViewMaterias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carrera);

        idmateri = new ArrayList<Integer>();
        carreraRepositorio = new CarreraRepositorioDblImpl(this);

        btnGuardarCarrera = (Button) findViewById(R.id.btnGuardarCarrera);
        btnCancelar = (Button) findViewById(R.id.btnCancelarCarrera);
        btnAdd = (Button) findViewById(R.id.btnAddMateria);

        etNombreCarrera = (EditText) findViewById(R.id.etNombreCarrera);

        Spinner spi = (Spinner) findViewById(R.id.spinnerMateria);
        loadSpinnerData(spi);


        spi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                materia = (Materia) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerViewMaterias = (RecyclerView) findViewById(R.id.recycleViewMaterias);
        recyclerViewMaterias.setLayoutManager(new LinearLayoutManager(this));

        final AdaptadorMateria adapter = new AdaptadorMateria(new ArrayList<Materia>());
        recyclerViewMaterias.setAdapter(adapter);

        // Agregar elemento al recycleView
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (materia == null || materia.getId() == 0){
                    Intent intent = new Intent(v.getContext(), MateriaActivity.class);
                    startActivityForResult(intent,0);
                }else{
                   // Log.i("Codigo: ", ((Materia) ((Spinner)findViewById(R.id.spinnerMateria)).getSelectedItem()).getId().toString() );
                    mtposition = (Materia) ((Spinner)findViewById(R.id.spinnerMateria)).getSelectedItem();
                    idmateri.add(mtposition.getId());
                    adapter.getListaMateria().add(materia);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        btnGuardarCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNombreCarrera.getText().toString()==""){
                    mensaje = Toast.makeText(getApplicationContext(), "Debe ingresar el nombre de la carrera", Toast.LENGTH_SHORT);
                    mensaje.setGravity(Gravity.CENTER, 0, 0);
                    mensaje.show();
                }else if((recyclerViewMaterias.getChildCount()<=0)){
                    mensaje = Toast.makeText(getApplicationContext(), "Debe ingresar materias a la carrera", Toast.LENGTH_SHORT);
                    mensaje.setGravity(Gravity.CENTER, 0, 0);
                    mensaje.show();
                }else{
                    Carrera carr = new Carrera(0,"");
                    carr.setNombre(etNombreCarrera.getText().toString());

                    int idcarrera = carreraRepositorio.crear(carr);

                    setMensaje(mensaje,Integer.toString(idcarrera));

                    if(idcarrera>0){
                        setMensaje(mensaje,"ENTRO");
                        carreraRepositorio = new CarreraRepositorioDblImpl(getApplicationContext());
                        int resultado = carreraRepositorio.materia_carrera(idcarrera, (List<Integer>) idmateri);

                        if(resultado != 0){
                            setMensaje(mensaje,"Se ha creado la carrera exitosamente");
                        }else{
                           setMensaje(mensaje,"Hubo problemas al crear la carrera");
                        }
                    }
                }
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadSpinnerData((Spinner)findViewById(R.id.spinnerMateria));
    }

}
