package com.itla.prueba_db.repositorio;

import com.itla.prueba_db.entidad.Materia;

import java.util.List;

public interface MateriaRepositorio {
    void crear(Materia materia);
    void actualizar(Materia materias);
    void eliminar(Materia materia);
    Materia buscar(int id);
    List<Materia> listaMaterias();
}
