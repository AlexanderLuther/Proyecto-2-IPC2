package Backend.Controladores;
import Backend.Archivos.ManejadorArchivos;
import Backend.DBSM.UsuarioDAO;
import Backend.DummyClases.Perfil;
import Backend.DummyClases.Usuario;
import Backend.Excepciones.ExcepcionRegistroUsuario;
import Backend.DBSM.ConexionDB;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author helmuthluther
 */
@MultipartConfig
@WebServlet(name = "ControladorCreacionUsuario", urlPatterns = {"/ControladorUsuario"})
public class ControladorCreacionUsuario extends HttpServlet {
    
    private ConexionDB conexionDB = new ConexionDB();
    private UsuarioDAO manejadorUsuariosDB = new UsuarioDAO();
    private ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
    private Usuario usuario;
    private Perfil perfil;
    private PrintWriter flujoSalida;
    private String direccionJSP;
    private String URLFotoPerfil;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            usuario = new Usuario(request);
            URLFotoPerfil = manejadorArchivos.guardarArchivo(1, request, response);
            perfil = new Perfil(request);
            perfil.setURLFotoPerfil(URLFotoPerfil);
            manejadorUsuariosDB.crearUsuario(usuario, perfil, conexionDB.getConexion());

            switch(usuario.getTipo()){
                case 1:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-administrador.jsp";
                break;
                case 2:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-editor.jsp";
                break;
                case 3:
                    direccionJSP = "/SistemaWebDeRevistas/jsp/frontend-suscriptor.jsp";
                break;
            }
            if(usuario.getTipo() == 2 || usuario.getTipo() == 3){
                request.getSession().setAttribute("usuario", usuario.getNombreUsuario());
                request.getSession().setAttribute("tipo", usuario.getTipo());
                request.getSession().setAttribute("actualizar", "si");
            }
            response.sendRedirect(direccionJSP);         
        }
        catch(ExcepcionRegistroUsuario e ){
            flujoSalida =new PrintWriter(response.getOutputStream());
            flujoSalida.println(e);
            flujoSalida.close();
        }
    }
}
