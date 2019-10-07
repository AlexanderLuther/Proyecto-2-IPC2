package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.RevistaDAO;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorMostrarRevistasSuscriptor", urlPatterns = {"/jsp/ControladorMostrarRevistasSuscriptor"})
public class ControladorMostrarRevistasSuscriptor extends HttpServlet {

    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("listadoRevistas");
        request.getSession().setAttribute("listadoRevistas", manejadorRevistaDB.obtenerRevistasAdministrador(manejadorConexionDB.getConexion()));
        response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-suscriptor.jsp");
    }
}
