package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.PagoDAO;
import Backend.DBSM.PublicacionDAO;
import Backend.DBSM.SuscripcionDAO;
import Backend.DummyClases.Suscripcion;
import Backend.Fechas.ManejadorFechas;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Backend.DummyClases.Pago;
/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorSuscripcion", urlPatterns = {"/ControladorSuscripcion"})
public class ControladorSuscripcion extends HttpServlet {

    private SuscripcionDAO manejadorSuscripcionDB = new SuscripcionDAO();
    private PublicacionDAO manejadorPublicacionDB = new PublicacionDAO();
    private ManejadorFechas manejadorFechas = new ManejadorFechas();
    private ConexionDB manejadorConexionDB = new ConexionDB();
    private PagoDAO manejadorPagoDB = new PagoDAO();
    private Suscripcion suscripcion;
    private Pago pago;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener el Id para la nueva suscripcion
        request.setAttribute("idSuscripcion", manejadorSuscripcionDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        
        //Obtener el idPublicacion de la revista seleccionada
        request.setAttribute("idPublicacion", manejadorPublicacionDB.obtenerId(manejadorConexionDB.getConexion(), Integer.parseInt(request.getParameter("r"))));
        
        //Obtener la fecha de suscripcion e inicio
        request.setAttribute("fechaSuscripcion", manejadorFechas.convertirFecha(request.getParameter("fecha")));
        
        //Obtener la fecha de fin
        request.setAttribute("fechaFin", manejadorFechas.obtenerFechaFin(manejadorFechas.convertirFecha(request.getParameter("fecha"))));
        
        //Obtener el valor (activa/inactiva) actual de la suscripcion
        request.setAttribute("activa", manejadorFechas.compararFechas(manejadorFechas.convertirFecha(request.getAttribute("fechaFin").toString())));
    
        //Crear una nueva instancia del objeto suscripcion y guardarlo en la base de datos
        suscripcion = new Suscripcion(request);
        manejadorSuscripcionDB.agregarSuscripcion(suscripcion, manejadorConexionDB.getConexion());
        
        //Obtener idPago para el nuevo pago
        request.setAttribute("idPago", manejadorPagoDB.obtenerNuevoId(manejadorConexionDB.getConexion()));
        
        //Obtener monto
        request.setAttribute("monto", manejadorPublicacionDB.obtenerCuotaSuscripcion(manejadorConexionDB.getConexion(), Integer.parseInt(request.getAttribute("idPublicacion").toString())));
            
        //Establecer la fecha de pago
        request.setAttribute("fechaPago", manejadorFechas.convertirFecha(request.getParameter("fecha")));
        
        //Crear una nueva instancia del objeto pago y guardarlo en la base de datos
        pago = new Pago(request);
        manejadorPagoDB.agregarPago(pago, manejadorConexionDB.getConexion());
        
        response.sendRedirect("/SistemaWebDeRevistas/jsp/ControladorVerRevista?idRevista="+request.getParameter("r"));
        
    }

}
