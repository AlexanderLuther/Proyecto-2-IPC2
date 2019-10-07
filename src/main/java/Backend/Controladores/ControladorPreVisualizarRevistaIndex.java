package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.MarcadorDAO;
import Backend.DBSM.MeGustaDAO;
import Backend.DBSM.PublicacionDAO;
import Backend.DBSM.RevistaDAO;
import Backend.DBSM.SuscripcionDAO;
import Backend.DummyClases.Marcador;
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
@WebServlet(name = "ControladorPreVisualizarRevistaIndex", urlPatterns = {"/jsp/ControladorPreVisualizarRevistaIndex"})
public class ControladorPreVisualizarRevistaIndex extends HttpServlet {
    
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private PublicacionDAO manejadorPublicacionDB = new PublicacionDAO();
    private SuscripcionDAO manejadorSuscripcionDB = new SuscripcionDAO();
    private MarcadorDAO manejadorMarcadorDB = new MarcadorDAO();
    private MeGustaDAO manejadorMeGustaDB = new MeGustaDAO();
    private RequestDispatcher dispatcher;
    private Revista revista;
    private Marcador marcador;
    private int idPublicacion;
    private String nombreUsuario;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener la revista seleccionada y guardarla en el request.
        revista = manejadorRevistaDB.obtenerRevista(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("idRevista")));
        request.setAttribute("revistaSeleccionada", revista);
        
        //Obtener las categorias de la revista y guardarla en el request
        marcador = manejadorMarcadorDB.obtenerMarcador(manejadorConexionDB.getConexion(), revista.getIdRevista(), 1);
        request.setAttribute("categorias", marcador);
        
        //Obtener las etiquetas de la revista y guardarla en el request
        marcador = manejadorMarcadorDB.obtenerMarcador(manejadorConexionDB.getConexion(), revista.getIdRevista(), 2);
        request.setAttribute("etiquetas", marcador);
        
        //Obtener la cantidad de MeGusta y guardarlo en el request
        request.setAttribute("cantidadMeGusta", manejadorMeGustaDB.obtenerCantidadMeGusta(manejadorConexionDB.getConexion(), revista.getIdRevista()));
        
        dispatcher = request.getRequestDispatcher("/jsp/previsualizar-revista-index.jsp");
        dispatcher.forward(request, response);
    }

}
