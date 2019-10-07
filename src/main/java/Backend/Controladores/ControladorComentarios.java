package Backend.Controladores;

import Backend.DBSM.ComentarioDAO;
import Backend.DBSM.ConexionDB;
import Backend.Fechas.ManejadorFechas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Backend.DummyClases.Comentario;
/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorComentarios", urlPatterns = {"/jsp/ControladorComentarios"})
public class ControladorComentarios extends HttpServlet {
    
    private ComentarioDAO manejadorComentariosDB = new ComentarioDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private Comentario comentario;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener el id del nuevo comentario
        request.setAttribute("idComentario", manejadorComentariosDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        
        //Obtener el id de la revista
        request.setAttribute("idRevista", request.getParameter("r"));
        
        //Obtener la fecha de comentario
        request.setAttribute("fechaComentario", manejadorFechas.convertirFecha(request.getParameter("fecha")));
        
        //Crear una nueva intancia del objeto comentario y guardarla en la base de datos
        comentario = new Comentario(request);
        manejadorComentariosDB.agregarComentario(comentario, manejadorConexionDB.getConexion());
        
        response.sendRedirect("/SistemaWebDeRevistas/jsp/ControladorVerRevista?idRevista="+request.getAttribute("idRevista"));
    }
}
