package com.itla.prueba_db.entidad;

public class Carrera {
    private int id;
    private String nombre;
    private Integer cantidad_materia;
    private Integer creditos;

    public Integer getCantidad_materia() {
        return cantidad_materia;
    }

    public void setCantidad_materia(Integer cantidad_materia) {
        this.cantidad_materia = cantidad_materia;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
