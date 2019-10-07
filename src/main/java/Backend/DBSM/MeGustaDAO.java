package Backend.DBSM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author helmuthluther
 */
public class MeGustaDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    
    public int obtenerCantidadMeGusta(Connection conexion, int idRevista){
        try {
            declaracion = conexion.prepareStatement("SELECT COUNT(*) AS Cantidad FROM MeGusta WHERE IdRevista = ?;");
            declaracion.setInt(1, idRevista);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return resultado.getInt("Cantidad");
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos");
        }
        return 0;
    }
}
