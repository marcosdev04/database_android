package com.itla.prueba_db.entidad;

public class Carrera {
    private int id;
    private String nombre;
    private Integer cantidad_materia;
    private Integer creditos;
    private Integer idcarrera;
    private Integer idmateria;

    public Carrera(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getIdcarrera() {
        return idcarrera;
    }

    public void setIdcarrera(Integer idcarrera) {
        this.idcarrera = idcarrera;
    }

    public Integer getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(Integer idmateria) {
        this.idmateria = idmateria;
    }

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

    @Override
    public String toString() {
        return  nombre;
    }
}
