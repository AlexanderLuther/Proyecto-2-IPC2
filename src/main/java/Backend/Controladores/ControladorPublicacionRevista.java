package Backend.Controladores;

import Backend.Archivos.ManejadorArchivos;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
import Backend.DBSM.MarcadorDAO;
import Backend.DBSM.PublicacionDAO;
import Backend.DBSM.RevistaDAO;
import Backend.DummyClases.Marcador;
import Backend.DummyClases.Publicacion;
import Backend.DummyClases.Revista;
import Backend.Fechas.ManejadorFechas;
import Backend.DummyClases.Costo;
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
@WebServlet(name = "ControladorPublicacionRevista", urlPatterns = {"/ControladorPublicacionRevista"})
public class ControladorPublicacionRevista extends HttpServlet {

    private CostosDAO manejadorCostosDB = new CostosDAO();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private PublicacionDAO manejadorPublicacionDB = new PublicacionDAO();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
    private MarcadorDAO manejadorMarcadoresDB = new MarcadorDAO();
    private Costo costo;
    private Marcador marcador;
    private Revista revista;
    private Publicacion publicacion;
    private String URLPDF;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener los parametros restantes que necesita el objeto revista
        request.setAttribute("costo", manejadorCostosDB.obtenerCostoGlobal(manejadorConexionDB.getConexion()));
        request.setAttribute("idRevista", manejadorRevistaDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        request.setAttribute("fecha", manejadorFechas.convertirFecha(request.getParameter("fechaCreacion")));
        
        //Crear una nueva instancia del objeto Revista
        revista = new Revista(request, 1);
        
        //Guardar en el servidor la revista subida al sistema
        URLPDF = manejadorArchivos.guardarArchivo(2, request, response);
        revista.setURLPDF(URLPDF);
        
        //Guarda en el DBSM la revista
        manejadorRevistaDB.agregarRevista(revista, manejadorConexionDB.getConexion());
        
        //Obtener los parametros restantes que necesita el objeto Publicacion
        request.setAttribute("idPublicacion", manejadorPublicacionDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        
        //Crear una nueva instancia del objeto Publicacion
        publicacion = new Publicacion(request, 1);
        manejadorPublicacionDB.agregarPublicacion(publicacion, manejadorConexionDB.getConexion());
        
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
