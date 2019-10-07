package Backend.DBSM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Backend.DummyClases.Pago;

/**
 *
 * @author helmuthluther
 */
public class PagoDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdPago FROM Pago ORDER BY IdPago DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdPago") + 1);
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return 0;
    }
    
    public void agregarPago(Pago pago, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Pago VALUES(?,?,?,?);");
            declaracion.setInt(1, pago.getIdPago());
            declaracion.setInt(2, pago.getIdPublicacion());
            declaracion.setDouble(3, pago.getMonto());
            declaracion.setTimestamp(4, pago.getFechaPago());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }   
    }
    
}
