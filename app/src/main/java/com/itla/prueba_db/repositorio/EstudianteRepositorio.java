package com.itla.prueba_db.repositorio;

import com.itla.prueba_db.entidad.Estudiante;

import java.util.List;

public interface EstudianteRepositorio {
    void crear(Estudiante estudiante);
    void actualizar(Estudiante estudiante);
    void eliminar(Estudiante estudiante);
    Estudiante buscar(int id);
    List<Estudiante> buscar();
}
