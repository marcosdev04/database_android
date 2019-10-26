package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.entidad.Materia;
import com.itla.prueba_db.repositorio.MateriaRepositorio;
import com.itla.prueba_db.repositorio.MateriaRepositorioDbImpl;

public class MateriaActivity extends AppCompatActivity {

    MateriaRepositorio materiaRepositorio;
    EditText edCreditos, edNombre;
    Button btnGuardar;
    Integer credito;

    public void Limpiar(ConstraintLayout layout){
        for (int i=0; i < layout.getChildCount(); i++){
            View v = layout.getChildAt(i);
            if (v instanceof EditText){
                ((EditText) v).setText("");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia);

        materiaRepositorio = new MateriaRepositorioDbImpl(this);
        edCreditos = findViewById(R.id.Creditos);
        edNombre = findViewById(R.id.nombreMateria);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Materia mat = new Materia();
                mat.setNombre(edNombre.getText().toString());
                credito = Integer.parseInt(edCreditos.getText().toString());
                mat.setCreditos(credito);

                materiaRepositorio.crear(mat);
                Limpiar((ConstraintLayout) findViewById(R.id.constraintMateria));
            }
        });

    }
}
