/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.RevistaDAO;
import Backend.DummyClases.Revista;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorModificarRevista", urlPatterns = {"/jsp/ControladorModificarRevista"})
public class ControladorModificarRevistaEditor extends HttpServlet {

    private ConexionDB manejadorConexionDB = new ConexionDB();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private RequestDispatcher dispatcher;
    private Revista revistaInicial;
    private Revista revistaFinal;
    private boolean bloquearComentarios;
    private boolean bloquearSuscripciones;
    private boolean bloquearMeGusta;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        revistaInicial = manejadorRevistaDB.obtenerRevista(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("idRevista")));
        request.setAttribute("revistaSeleccionada", revistaInicial);
        dispatcher = request.getRequestDispatcher("/jsp/modificar-revista-editor.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!request.getParameter("comentarios").equals("true")) this.bloquearComentarios = true;
        else this.bloquearComentarios = false;
        if(!request.getParameter("suscripciones").equals("true")) this.bloquearSuscripciones = true;
        else this.bloquearSuscripciones = false;
        if(!request.getParameter("meGusta").equals("true")) this.bloquearMeGusta = true;
        else this.bloquearMeGusta = false;
        System.out.println(revistaInicial.getIdRevista());
        revistaFinal = new Revista(revistaInicial.getIdRevista(), revistaInicial.getNombreUsuarioAutor(), revistaInicial.getNombre(), revistaInicial.getURLPDF(), revistaInicial.getDescripcion(), revistaInicial.getAutor(),
                                    revistaInicial.getVolumen(), revistaInicial.getCostoPorDia(), revistaInicial.getCuotaSuscripcion(), bloquearComentarios, bloquearMeGusta, bloquearSuscripciones, revistaInicial.getFechaCreacion());
        manejadorRevistaDB.modificarRevista(revistaFinal, manejadorConexionDB.getConexion());
        request.getSession().setAttribute("actualizar", "si");
        response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-editor.jsp");
    }
}
