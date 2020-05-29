package backend_estudiante_curso;

import backend_estudiante_curso.dao.gestor_Estudiante;
import backend_estudiante_curso.modelo.estudiante;
import java.util.List;

public class main {

    public static void main(String[] args) {
        
        
        gestor_Estudiante.obtenerInstancia().insertarEstudiante(1111,"AAAA","AAAA", 23);
        gestor_Estudiante.obtenerInstancia().actualizarEstudiante(1111, "AAAA", "AAAA", 27);
        
        List<estudiante> estudiantes = gestor_Estudiante.obtenerInstancia().listarEstudiantes();
        
        for(estudiante est: estudiantes)
            System.out.println(est.getId()+" "+est.getNombre()+" "+est.getApellido()+" "+est.getEdad());
        
        estudiante est= gestor_Estudiante.obtenerInstancia().recuperarEstudiante(117280151);
         System.out.println(est.getId()+" "+est.getNombre()+" "+est.getApellido()+" "+est.getEdad());
         
         gestor_Estudiante.obtenerInstancia().eliminarEstudiante(1111);
         
         List<estudiante> estudiantes2 = gestor_Estudiante.obtenerInstancia().listarEstudiantes();
         
         for(estudiante est2: estudiantes)
            System.out.println(est2.getId()+" "+est2.getNombre()+" "+est2.getApellido()+" "+est2.getEdad());
         
    
    }

}
