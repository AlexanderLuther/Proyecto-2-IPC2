package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.PerfilDAO;
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
@WebServlet(name = "ControladorPerfil", urlPatterns = {"/jsp/ControladorPerfil"})
public class ControladorPerfil extends HttpServlet {

    private String nombreUsuario;
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private PerfilDAO manejadorPerfilDB = new PerfilDAO();
    private RequestDispatcher dispatcher;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            if(!request.getParameter("nombreAutor").isEmpty()){
                nombreUsuario = request.getParameter("nombreAutor");
                request.setAttribute("ocultar", "si");
            }
            else{
                nombreUsuario = request.getSession().getAttribute("usuario").toString();
            }
        }
        catch(NullPointerException e){
            nombreUsuario = request.getSession().getAttribute("usuario").toString();
        }
        request.setAttribute("perfil", manejadorPerfilDB.obtenerPerfil(nombreUsuario, manejadorConexionDB.getConexion()));
        dispatcher = request.getRequestDispatcher("/jsp/perfil.jsp");
        dispatcher.forward(request, response);
    }

}
