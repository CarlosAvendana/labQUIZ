/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend_estudiante_curso.modelo;

import backend_estudiante_curso.dao.gestor_Estudiante;
import backend_estudiante_curso.dao.gestor_Matricula;
import java.util.Observable;

/**
 *
 * @author Luis Felipe
 */
public class Model{
    gestor_Estudiante gestorEstudiante;
    gestor_Matricula gestorMatricula;

    public Model() {
        this.gestorEstudiante=new gestor_Estudiante();
        this.gestorMatricula=new gestor_Matricula();
    }

    public gestor_Estudiante getGestorEstudiante() {
        return gestorEstudiante;
    }

    public void setGestorEstudiante(gestor_Estudiante gestorEstudiante) {
        this.gestorEstudiante = gestorEstudiante;
    }

    public gestor_Matricula getGestorMatricula() {
        return gestorMatricula;
    }

    public void setGestorMatricula(gestor_Matricula gestorMatricula) {
        this.gestorMatricula = gestorMatricula;
    }
    
    
    
}
