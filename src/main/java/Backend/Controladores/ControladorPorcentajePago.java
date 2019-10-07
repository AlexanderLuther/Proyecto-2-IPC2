package Backend.Controladores;

import Backend.Excepciones.ExcepcionRegistroUsuario;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
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
@WebServlet(name = "ControladorPorcentajePago", urlPatterns = {"/ControladorPorcentajePago"})
public class ControladorPorcentajePago extends HttpServlet {
    
    private ConexionDB conexion = new ConexionDB();
    private CostosDAO manejadorCostosDB = new CostosDAO();
    private double porcentajePago;
    private PrintWriter flujoSalida;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            porcentajePago = Double.parseDouble(request.getParameter("porcentajePago"));
            manejadorCostosDB.establecerPorcentajePagoGlobal(porcentajePago, conexion.getConexion());
            response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-administrador.jsp");
        }
        catch(ExcepcionRegistroUsuario e ){
            flujoSalida =new PrintWriter(response.getOutputStream());
            flujoSalida.println(e);
            flujoSalida.close();
        }
    }

}
