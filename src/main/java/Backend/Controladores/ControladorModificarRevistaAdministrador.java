package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
import Backend.DBSM.RevistaDAO;
import Backend.DummyClases.Costo;
import Backend.DummyClases.Revista;
import Backend.Fechas.ManejadorFechas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
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
@WebServlet(name = "ControladorModificarRevistaAdministrador", urlPatterns = {"/jsp/ControladorModificarRevistaAdministrador"})
public class ControladorModificarRevistaAdministrador extends HttpServlet {
    
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private RevistaDAO manejadorRevistaDB = new RevistaDAO();
    private CostosDAO manejadorCostosDB = new CostosDAO();
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private RequestDispatcher dispatcher;
    private Costo costo;
    private Revista revista;
    private double costoRevista;
    private Timestamp fecha;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        revista = manejadorRevistaDB.obtenerRevista(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("idRevista")));
        request.setAttribute("revistaSeleccionada", revista);
        dispatcher = request.getRequestDispatcher("/jsp/modificar-revista-administrador.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("fechaActualizacion", manejadorFechas.convertirFecha(request.getParameter("fecha")));
        costoRevista = Double.parseDouble(request.getParameter("costoRevista"));
        fecha = Timestamp.valueOf(request.getAttribute("fechaActualizacion").toString());
        
        //Actualizar el valor correspondiente al costo de la revista seleccionada
        manejadorCostosDB.modificarCostoRevista(manejadorConexionDB.getConexion(),costoRevista, revista.getIdRevista());
        
        //Crear una nueva instancia de la clase Costo y guardarla en la base de datos
        costo = new Costo(manejadorCostosDB.obtenerNuevoId(manejadorConexionDB.getConexion()), revista.getIdRevista(), costoRevista, fecha);
        manejadorCostosDB.guardarNuevoCosto(manejadorConexionDB.getConexion(), costo);
        
        //Redirigir a la pagina principal del administrador
        request.getSession().setAttribute("actualizar", "si");
        response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-administrador.jsp");
    }
}
