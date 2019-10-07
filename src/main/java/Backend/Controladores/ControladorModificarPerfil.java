package Backend.Controladores;

import Backend.Archivos.ManejadorArchivos;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.PerfilDAO;
import Backend.DBSM.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Backend.DummyClases.Perfil;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author helmuthluther
 */
@MultipartConfig
@WebServlet(name = "ControladorModificarPerfil", urlPatterns = {"/jsp/ControladorModificarPerfil"})
public class ControladorModificarPerfil extends HttpServlet {
    
    private PerfilDAO manejadorPerfilDB = new PerfilDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
    private UsuarioDAO manejadorUsuariosDB = new UsuarioDAO();
    private RequestDispatcher dispatcher;
    private Perfil perfil;
    private String URLFotoPerfil;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       request.setAttribute("perfilSolicitado", manejadorPerfilDB.obtenerPerfil(request.getSession().getAttribute("usuario").toString(), manejadorConexionDB.getConexion()));
       dispatcher = request.getRequestDispatcher("/jsp/modificar-perfil.jsp");
       dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        URLFotoPerfil = manejadorArchivos.guardarArchivo(1, request, response);
        perfil = new Perfil(request);
        perfil.setURLFotoPerfil(URLFotoPerfil);
        manejadorUsuariosDB.modificarPerfil(perfil, manejadorConexionDB.getConexion());
        response.sendRedirect("/SistemaWebDeRevistas/jsp/ControladorPerfil");
    }


}
