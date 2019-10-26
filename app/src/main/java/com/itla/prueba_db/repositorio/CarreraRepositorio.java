package com.itla.prueba_db.repositorio;


import com.itla.prueba_db.entidad.Carrera;

import java.util.List;

public interface CarreraRepositorio {

    void crear(Carrera carrera);
    void actualizar(Carrera carreras);
    void eliminar(Carrera carrera);
    Carrera buscar(int id);
    List<Carrera> CarreraDetallada();
    List<String> listaCarrera();

}
