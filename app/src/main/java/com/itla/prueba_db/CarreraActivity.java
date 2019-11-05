package com.itla.prueba_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.repositorio.CarreraRepositorio;
import com.itla.prueba_db.repositorio.CarreraRepositorioDblImpl;

import java.util.ArrayList;
import java.util.List;

public class CarreraActivity extends AppCompatActivity {

    CarreraRepositorio carreraRepositorio;
    Toast msj;
    Button btnNewCarrera;
    RecyclerView recyclerViewCarrera;
    AdaptadorCarrera adaptadorCarrera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera);

        btnNewCarrera = findViewById(R.id.btnNewCarrera);

        carreraRepositorio = new CarreraRepositorioDblImpl(getApplicationContext());

        List<Carrera> carreras = carreraRepositorio.CarreraDetallada();

        if(carreras.size() < 0)
        {
            msj = Toast.makeText(getApplicationContext(),"NO HAY DATOS" , Toast.LENGTH_SHORT);
            msj.setGravity(Gravity.CENTER, 0, 0);
            msj.show();
            setDataReport(carreras);
        }else{
            msj = Toast.makeText(getApplicationContext(),"HAY DATOS" , Toast.LENGTH_SHORT);
            msj.setGravity(Gravity.CENTER, 0, 0);
            msj.show();
            setDataReport(carreras);
        }

        btnNewCarrera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarrera = new Intent(v.getContext(), CrearCarrera.class);
                startActivityForResult(intentCarrera,0);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        carreraRepositorio = new CarreraRepositorioDblImpl(getApplicationContext());

        List<Carrera> carreras = carreraRepositorio.CarreraDetallada();

        setDataReport(carreras);
    }

    void setDataReport(List<Carrera> carreras){

        recyclerViewCarrera = (RecyclerView) findViewById(R.id.recyclerViewCarrera);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCarrera.setLayoutManager(layoutManager);

        adaptadorCarrera = new AdaptadorCarrera(carreras);
        recyclerViewCarrera.setAdapter(adaptadorCarrera);
    }

}