package com.itla.prueba_db.repositorio;


import com.itla.prueba_db.entidad.Carrera;
import com.itla.prueba_db.entidad.Materia;

import java.util.List;

public interface CarreraRepositorio {

    int crear(Carrera carrera);
    int materia_carrera(int idcarrera, List<Integer> idmateria);
    void actualizar(Carrera carreras);
    void eliminar(Carrera carrera);
    Carrera buscar(int id);
    List<Carrera> CarreraDetallada();
    List<Carrera> listaCarrera();

}
