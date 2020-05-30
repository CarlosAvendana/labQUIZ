package com.example.quiz.logic;

import java.io.Serializable;

public class Curso implements Serializable {


    private int idcurso;
    private String descripcion;
    private int cresitos;

    public Curso() {
    }

    public Curso(int idcurso, String descripcion, int cresitos) {
        this.idcurso = idcurso;
        this.descripcion = descripcion;
        this.cresitos = cresitos;
    }

    @Override
    public String toString() {
        return "curso{" + "idcurso=" + idcurso + ", descripcion=" + descripcion + ", cresitos=" + cresitos + '}';
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCresitos() {
        return cresitos;
    }

    public void setCresitos(int cresitos) {
        this.cresitos = cresitos;
    }

}
