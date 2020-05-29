
package backend_estudiante_curso.dao;

import backend_estudiante_curso.managers.DBManager;
import backend_estudiante_curso.modelo.estudiante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class gestor_Estudiante {
     private static gestor_Estudiante instancia=null;
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private String URL_Servidor = "localhost";
    private static final String BASE_DATOS = "estudiantes_curso";
    private static final String DATABASE_DRIVER
            = "com.mysql.cj.jdbc.Driver";
    private static final String CONEXION
            = "jdbc:mysql://localhost/estudiantes_curso";
    
    private static final String INSERTARESTUDIANTE="{call PRC_INS_ESTUDIANTE(?,?,?,?)}";
    private static final String ACTUALIZARESTUDIANTE="{call PRC_UPD_ESTUDIANTE(?,?,?,?)}";
    private static final String ELIMINARESTUDIANTE="{call PRC_DEL_ESTUDIANTE(?)}";
    private static final String OBTIENEUNESTUDIANTE="{call PRC_OBTIENE_UN_ESTUDIANTE(?)}";
    private static final String OBTIENEESTUDIANTES="{call PRC_OBTIENE_ESTUDIANTES()}";
    
    
    public gestor_Estudiante(){
         try {
            Class.forName(DATABASE_DRIVER).newInstance();

        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }
    }
    
    public static gestor_Estudiante obtenerInstancia(){
        if (instancia == null) {
            instancia = new gestor_Estudiante();
        }
        return instancia;
    }
    
    public void insertarEstudiante(int id,String nom,String ape,int ed){
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(INSERTARESTUDIANTE)) {
                stm.clearParameters();
                stm.setInt(1, id);
                stm.setString(2, nom);
                stm.setString(3, ape);
                stm.setInt(4, ed);
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
        
   public boolean actualizarEstudiante(int id,String nom,String ape,int ed) {
        boolean exito = false;
        int registrosActualizados = 0;
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(ACTUALIZARESTUDIANTE)) {
                stm.clearParameters();
                stm.setInt(1, id);
                stm.setString(2, nom);
                stm.setString(3, ape);
                stm.setInt(4, ed);

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
   
    public boolean eliminarEstudiante(int id) {
        boolean exito = false;
        int registrosActualizados = 0;
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);
            try (PreparedStatement stm = cnx.prepareStatement(ELIMINARESTUDIANTE)) {
                stm.clearParameters();
                stm.setInt(1, id);
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
    
    public estudiante recuperarEstudiante(int id){
    estudiante _estudiante = null;
        DBManager bd = null;
        try {
            bd = DBManager.getDBManager(DBManager.DB_MGR.MYSQL_SERVER, URL_Servidor);
            Connection cnx
                    = bd.getConnection(BASE_DATOS, LOGIN, PASSWORD);

            try (PreparedStatement stm = cnx.prepareStatement(OBTIENEUNESTUDIANTE)) {
                stm.clearParameters();
                stm.setInt(1, id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    _estudiante = new estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),rs.getInt("edad"));
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
        return _estudiante;
    
    }
    
    public List<estudiante> listarEstudiantes(){
        List<estudiante> array_Estudiantes = new ArrayList<>();
        try (Connection cnx = DriverManager.getConnection(
                CONEXION, LOGIN, PASSWORD);
                Statement stm = cnx.createStatement();
                ResultSet rs = stm.executeQuery(OBTIENEESTUDIANTES)) {
            while (rs.next()) {
                array_Estudiantes.add(new estudiante(rs.getInt("id"), rs.getString("nombre"), rs.getString("apellido"),rs.getInt("edad")));
            }
        } catch (SQLException ex) {
            System.err.printf("Excepción: '%s'%n", ex.getMessage());
        }

        return array_Estudiantes;
    }

}
