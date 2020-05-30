package com.example.quiz.logic;


import java.io.Serializable;
import java.util.Objects;

public class Matricula implements Serializable {

    private int estudiante_id;
    private int curso_idcurso;

    public Matricula() {
    }

    public Matricula(int estudiante_id, int curso_idcurso) {
        this.estudiante_id = estudiante_id;
        this.curso_idcurso = curso_idcurso;
    }


    public int getEstudiante_id() {
        return estudiante_id;
    }

    public void setEstudiante_id(int estudiante_id) {
        this.estudiante_id = estudiante_id;
    }

    public int getCurso_idcurso() {
        return curso_idcurso;
    }

    public void setCurso_idcurso(int curso_idcurso) {
        this.curso_idcurso = curso_idcurso;
    }


    @Override
    public String toString() {
        return "Matricula{" +
                "estudiante_id=" + estudiante_id +
                ", curso_idcurso=" + curso_idcurso +
                '}';
    }
}
