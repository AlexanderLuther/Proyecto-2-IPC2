package Backend.Controladores;

import Backend.DummyClases.Usuario;
import Backend.Excepciones.ExcepcionRegistroUsuario;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.RevistaDAO;
import Backend.DBSM.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorInicioSesion", urlPatterns = {"/ControladorInicioSesion"})
public class ControladorInicioSesion extends HttpServlet {
 
    private UsuarioDAO manejadorUsuarioDB = new UsuarioDAO();
    private ConexionDB manejadorConexioDB = new ConexionDB();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private PrintWriter flujoSalida;
    private Usuario usuario;
    private String direccionJSP;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            usuario = new Usuario(request.getParameter("nombreUsuario"), request.getParameter("contrasena"));
            usuario.setTipo(manejadorUsuarioDB.iniciarSesion(usuario, manejadorConexioDB.getConexion()));
            switch(usuario.getTipo()){
                case 1:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-administrador.jsp";
                    request.getSession().setAttribute("listadoRevistas", manejadorRevistaDB.obtenerRevistasAdministrador(manejadorConexioDB.getConexion()));             
                break;
                case 2:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-editor.jsp";
                    request.getSession().setAttribute("listadoRevistas", manejadorRevistaDB.obtenerRevistasEditor(manejadorConexioDB.getConexion(), usuario.getNombreUsuario()));
                break;
                case 3:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-suscriptor.jsp";
                break;
            }
            request.getSession().setAttribute("usuario", usuario.getNombreUsuario());
            request.getSession().setAttribute("tipo", usuario.getTipo());
            response.sendRedirect(direccionJSP);
        } 
        catch (ExcepcionRegistroUsuario ex) {
            flujoSalida =new PrintWriter(response.getOutputStream());
            flujoSalida.println(ex);
            flujoSalida.close();
        }  
    }
    
}
