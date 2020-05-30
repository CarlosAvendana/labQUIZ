package com.example.quiz.LogicaNeg;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso curso = (Curso) o;
        return getIdcurso() == curso.getIdcurso() &&
                getCresitos() == curso.getCresitos() &&
                Objects.equals(getDescripcion(), curso.getDescripcion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdcurso(), getDescripcion(), getCresitos());
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idcurso=" + idcurso +
                ", descripcion='" + descripcion + '\'' +
                ", cresitos=" + cresitos +
                '}';
    }
}
