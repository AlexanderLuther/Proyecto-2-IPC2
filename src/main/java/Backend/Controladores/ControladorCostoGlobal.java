package Backend.Controladores;

import Backend.Excepciones.ExcepcionRegistroUsuario;
import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author helmuthluther
 */
@WebServlet(name = "ControladorCostoGlobal", urlPatterns = {"/ControladorCostoGlobal"})
public class ControladorCostoGlobal extends HttpServlet {
    
    private ConexionDB conexion = new ConexionDB();
    private CostosDAO manejadorCostosDB = new CostosDAO();
    private double costoGlobal;
    private PrintWriter flujoSalida;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            costoGlobal = Double.parseDouble(request.getParameter("costoGlobal"));
            manejadorCostosDB.establecerCostoGlobal(costoGlobal, conexion.getConexion());
            response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-administrador.jsp");
        } 
        catch (ExcepcionRegistroUsuario ex) {
            flujoSalida =new PrintWriter(response.getOutputStream());
            flujoSalida.println(ex);
            flujoSalida.close();
        }
    }
}

