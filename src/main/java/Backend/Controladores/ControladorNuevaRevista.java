package Backend.Controladores;

import Backend.Archivos.ManejadorArchivos;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
import Backend.DBSM.MarcadorDAO;
import Backend.DBSM.PublicacionDAO;
import Backend.DBSM.RevistaDAO;
import Backend.DummyClases.Costo;
import Backend.DummyClases.Marcador;
import Backend.DummyClases.Publicacion;
import Backend.DummyClases.Revista;
import Backend.Fechas.ManejadorFechas;
import java.io.IOException;
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
@WebServlet(name = "ControladorNuevaRevista", urlPatterns = {"/ControladorNuevaRevista"})
public class ControladorNuevaRevista extends HttpServlet {
    
    private PublicacionDAO manejadorPublicacionesDB = new PublicacionDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private CostosDAO manejadorCostosDB = new CostosDAO();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
    private MarcadorDAO manejadorMarcadoresDB = new MarcadorDAO();
    private Costo costo;
    private Marcador marcador;
    private Revista revista;
    private Publicacion publicacion;
    private String URLPDF;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("listadoPublicaciones", manejadorPublicacionesDB.obtenerPublicaciones(manejadorConexionDB.getConexion(), request.getSession().getAttribute("usuario").toString()));
        response.sendRedirect("/SistemaWebDeRevistas/jsp/agregar-revista.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        //Obtener los parametros restantes que necesita el objeto revista
        request.setAttribute("costo", manejadorCostosDB.obtenerCostoGlobal(manejadorConexionDB.getConexion()));
        request.setAttribute("idRevista", manejadorRevistaDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        request.setAttribute("fecha", manejadorFechas.convertirFecha(request.getParameter("fechaCreacion")));
        request.setAttribute("cuotaSuscripcion", manejadorPublicacionesDB.obtenerCuotaSuscripcion(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("idPublicacion"))));
        
        //Crear una nueva instancia del objeto Revista
        revista = new Revista(request,2);
        
        //Guardar en el servidor la revista subida al sistema
        URLPDF = manejadorArchivos.guardarArchivo(2, request, response);
        revista.setURLPDF(URLPDF);
        
        //Guarda en el DBSM la revista
        manejadorRevistaDB.agregarRevista(revista, manejadorConexionDB.getConexion());
        
        //Obtener los parametros restantes que necesita el objeto Publicacion
        request.setAttribute("nombrePublicacion", manejadorPublicacionesDB.obtenerNombrePublicacion(manejadorConexionDB.getConexion(),Integer.parseInt(request.getParameter("idPublicacion"))));
        
        //Crear una nueva instancia del objeto Publicacion y guardarlo en la base de datos
        publicacion = new Publicacion(request, 2);
        manejadorPublicacionesDB.agregarPublicacion(publicacion, manejadorConexionDB.getConexion());
        
        //Crear una nueva instancia del objeto marcador para almacenar una categoria y guardarla en la base de datos
        marcador = new Marcador(request, 1);
        manejadorMarcadoresDB.guardarMarcador(marcador, manejadorConexionDB.getConexion());
        
        //Crear una nueva instancia del objeto marcador para almacenar una etiqueta y guardarla en la base de datos
        marcador = new Marcador(request, 2);
        manejadorMarcadoresDB.guardarMarcador(marcador, manejadorConexionDB.getConexion());
        
        //Crear una nueva instancia de la clase Costo y guardarla en la base de datos
        costo = new Costo(manejadorCostosDB.obtenerNuevoId(manejadorConexionDB.getConexion()), revista.getIdRevista(), revista.getCostoPorDia(), revista.getFechaCreacion());
        manejadorCostosDB.guardarNuevoCosto(manejadorConexionDB.getConexion(), costo);
        
        request.getSession().setAttribute("actualizar", "si");
        response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-editor.jsp");
    }

}
