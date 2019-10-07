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
@WebServlet(name = "ControladorMostrarRevistasEditor", urlPatterns = {"/jsp/ControladorMostrarRevistasEditor"})
public class ControladorMostrarRevistasEditor extends HttpServlet {

    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.getSession().removeAttribute("listadoRevistas");
       request.getSession().setAttribute("listadoRevistas", manejadorRevistaDB.obtenerRevistasEditor(manejadorConexionDB.getConexion(), request.getSession().getAttribute("usuario").toString()));
       response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-editor.jsp");
    }

}
