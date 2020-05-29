
package backend_estudiante_curso.dao;

import backend_estudiante_curso.managers.DBManager;
import backend_estudiante_curso.modelo.curso;
import backend_estudiante_curso.modelo.matricula;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class gestor_Matricula {
    
    private static gestor_Matricula instancia=null;
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "estudiantes_curso";
    private static final String DATABASE_DRIVER
            = "com.mysql.cj.jdbc.Driver";
    private static final String CONEXION
            = "jdbc:mysql://localhost/estudiantes_curso";
    
    private static final String INSERTARMATRICULA="{call PRC_INS_MATRICULA(?,?)}";
    private static final String ACTUALIZARMATRICULA="{call PRC_UPD_MATRICULA(?,?)}";
    private static final String ELIMINARMATRICULA="{call PRC_DEL_MATRICULA(?,?)}";
    private static final String OBTIENEMATRICULA="{call PRC_OBTIENE_MATRICULA_ESTUDIANTE(?)}";
    
    private static final String OBTIENECURSOS="{call PRC_OBTIENE_CURSOS()}";
    
    
    public gestor_Matricula(){
         try {
            Class.forName(DATABASE_DRIVER).newInstance();

        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }
    
    public static gestor_Matricula obtenerInstancia(){
        if (instancia == null) {
            instancia = new gestor_Matricula();
        }
        return instancia;
    }
    
    public void insertarMatricula(int idCurso,int idEstudiante){
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(INSERTARMATRICULA)) {
                stm.clearParameters();
                stm.setInt(1, idCurso);
                stm.setInt(2, idEstudiante);
                stm.executeUpdate();
            }

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
    }
    
        public boolean actualizarMatricula(int idCurso,int idEstudiante) {
        boolean exito = false;
        int registrosActualizados = 0;
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(ACTUALIZARMATRICULA)) {
                stm.clearParameters();
                stm.setInt(1, idCurso);
                stm.setInt(2, idEstudiante);

                registrosActualizados = stm.executeUpdate();

                exito = registrosActualizados == 1;
            }

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
        return exito;
    }
        
    public boolean eliminarMatricula(int idCurso,int idEstudiante) {
        boolean exito = false;
        int registrosActualizados = 0;
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(ELIMINARMATRICULA)) {
                stm.clearParameters();
                stm.setInt(1, idCurso);
                stm.setInt(2, idEstudiante);
                registrosActualizados = stm.executeUpdate();
                exito = registrosActualizados == 1;
            }

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
        return exito;
    }
    
    public List<matricula> recuperarCarrera(int idEst) {
       List<matricula> _matricula = new ArrayList<matricula>();
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);

            try (PreparedStatement stm = cnx.prepareStatement(OBTIENEMATRICULA)) {
                stm.clearParameters();
                stm.setInt(1, idEst);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    _matricula.add(new matricula(rs.getInt("estudiante_id"),rs.getInt("curso_idcurso")));
                }
            }

        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            if (bd != null) {
                bd.closeConnection();
            }
        }
        return _matricula;
    }
    
    public List<curso> recuperarCursos(){
  List<curso> array_curso = new ArrayList<>();
        try (Connection cnx = DriverManager.getConnection(
                CONEXION, LOGIN, PASSWORD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(OBTIENECURSOS)) {
            while (rs.next()) {
                array_curso.add(new curso(rs.getInt("idcurso"), rs.getString("descripcion"),rs.getInt("creditos")));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        return array_curso;
    }
 
 

}
