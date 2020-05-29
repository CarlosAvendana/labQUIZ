/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import backend_estudiante_curso.modelo.Model;
import backend_estudiante_curso.modelo.estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Luis Felipe
 */
public class Server_Movil_Estudiante extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          
        }
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        Model modelo = new Model();

        String _id = request.getParameter("id");
        String _nombre = request.getParameter("nombre");
        String _apellido = request.getParameter("apellido");
        String _edad = request.getParameter("edad");
        
        int id = Integer.parseInt(_id);
        int edad = Integer.parseInt(_edad);

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<h5>Se insertara </h5>");
            //out.println("<h5>Profe: " + p.getId() + p.getNombre() + "</h5>");
            modelo.getGestorEstudiante().insertarEstudiante(id,_nombre,_apellido,edad);

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Model modelo = new Model();
        String opc = request.getParameter("opc");
        String id = request.getParameter("id");
        if (Integer.parseInt(opc) == 1) {

            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                //out.println("<h1>Se consultará </h1>");
                estudiante est = modelo.getGestorEstudiante().recuperarEstudiante(Integer.parseInt(id));

                JSONObject estudianteJSON = new JSONObject();
                estudianteJSON.put("id", est.getId());
                estudianteJSON.put("nombre", est.getNombre());
                estudianteJSON.put("apellido", est.getApellido());
                estudianteJSON.put("edad", est.getEdad());

                out.print(estudianteJSON);

            }
        } else {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                //out.println("<h1>Se listará </h1>");
                List<estudiante> _lista_estudiante = modelo.getGestorEstudiante().listarEstudiantes();
                JSONArray _estudiante_array_JS = new JSONArray();

                for (estudiante est : _lista_estudiante) {
                    JSONObject estudianteJSON = new JSONObject();
                    estudianteJSON.put("id", est.getId());
                    estudianteJSON.put("nombre", est.getNombre());
                    estudianteJSON.put("apellido", est.getApellido());
                    estudianteJSON.put("edad", est.getEdad());
                    _estudiante_array_JS.put(estudianteJSON);
                }

                out.print(_estudiante_array_JS);

            }//cierre del try

        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Model modelo = new Model();

        String _id = request.getParameter("id");
        String _nombre = request.getParameter("nombre");
        String _apellido = request.getParameter("apellido");
        String _edad = request.getParameter("edad");
        
         int id = Integer.parseInt(_id);
        int edad = Integer.parseInt(_edad);
        
          try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<h5>Se modificara </h5>");
            //  out.println("<h5>String " + p.getId() + p.getNombre() + "</h5>");
            modelo.getGestorEstudiante().actualizarEstudiante(id, _nombre, _apellido, edad);
        }
    }
    
      protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);

        Model model = new Model();
        int _id = Integer.parseInt(request.getParameter("id"));

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //out.println("<h5> Se eliminara </h5>");
            //out.println("<h5>" + id + "</h5>");
            model.getGestorEstudiante().eliminarEstudiante(_id);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
