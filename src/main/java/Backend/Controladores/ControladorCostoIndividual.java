package Backend.Controladores;

import Backend.DBSM.ConexionDB;
import Backend.DBSM.CostosDAO;
import Backend.Excepciones.ExcepcionRegistroUsuario;
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
@WebServlet(name = "ControladorCostoIndividual", urlPatterns = {"/ControladorCostoIndividual"})
public class ControladorCostoIndividual extends HttpServlet {

    private ConexionDB conexion = new ConexionDB();
    private CostosDAO manejadorCostosDB = new CostosDAO();
    private double costoRevista;
    private PrintWriter flujoSalida;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("id"));
        
       /* try {
           // costoRevista = Double.parseDouble(request.getParameter("costoRevista"));
            //manejadorCostosDB.establecerCostoGlobal(costoRevista, conexion.getConexion());
            //response.sendRedirect("/SistemaWebDeRevistas/jsp/frontend-administrador.jsp");
        } 
        catch (ExcepcionRegistroUsuario ex) {
            flujoSalida =new PrintWriter(response.getOutputStream());
            flujoSalida.println(ex);
            flujoSalida.close();
        }*/
    }








}


