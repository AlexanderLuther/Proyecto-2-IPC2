package Backend.Controladores;

import Backend.DBSM.ComentarioDAO;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.MarcadorDAO;
import Backend.DBSM.MeGustaDAO;
import Backend.DBSM.PublicacionDAO;
import Backend.DBSM.RevistaDAO;
import Backend.DBSM.SuscripcionDAO;
import Backend.DummyClases.Revista;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Backend.DummyClases.Marcador;

/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorVerRevista", urlPatterns = {"/jsp/ControladorVerRevista"})
public class ControladorVerRevista extends HttpServlet {

    private ConexionDB manejadorConexionDB = new ConexionDB();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private PublicacionDAO manejadorPublicacionDB = new PublicacionDAO();
    private SuscripcionDAO manejadorSuscripcionDB = new SuscripcionDAO();
    private MarcadorDAO manejadorMarcadorDB = new MarcadorDAO();
    private MeGustaDAO manejadorMeGustaDB = new MeGustaDAO();
    private ComentarioDAO manejadorComentarioDB = new ComentarioDAO();
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
        
        //Obtener los comentarios en la revista y guardarlos en el request
        request.setAttribute("listadoComentarios", manejadorComentarioDB.obtenerComentarios(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("idRevista"))));
        
   
        //Obtener el idPublicacion de la revista seleccionada
        idPublicacion = manejadorPublicacionDB.obtenerId(manejadorConexionDB.getConexion(), revista.getIdRevista());
        
        //Obtener el nombre de usuario
        nombreUsuario = request.getSession().getAttribute("usuario").toString();
        
        //Validar si existe una suscripcion a la revista y llamar al jsp correspondiente
        if(manejadorSuscripcionDB.verfificarSuscripcion(manejadorConexionDB.getConexion(), idPublicacion, nombreUsuario)){
            //Verificar que la suscripcion este vigente
            if(manejadorSuscripcionDB.verfificarVigenciaSuscripcion(manejadorConexionDB.getConexion(), idPublicacion, nombreUsuario)){
                dispatcher = request.getRequestDispatcher("/jsp/ver-revista.jsp");
            }
            else{
                dispatcher = request.getRequestDispatcher("/jsp/previsualizar-revista-suscripcion-vencida.jsp");
            }
        }
        else{
            dispatcher = request.getRequestDispatcher("/jsp/previsualizar-revista.jsp");
        }
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
