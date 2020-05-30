package backend_estudiante_curso.modelo;

import java.util.ArrayList;
import java.util.List;

public class estudiante {

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    List<curso> listaCursos;

    public estudiante(int id, String nombre, String apellido, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.listaCursos=new ArrayList<>();
    }

    public estudiante(int id, String nombre, String apellido, int edad, List<curso> listaCursos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.listaCursos = listaCursos;
    }
    
    

    public estudiante() {
    }

    @Override
    public String toString() {
        return "estudiante{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + '}';
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<curso> getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(List<curso> listaCursos) {
        this.listaCursos = listaCursos;
    }
    

}
