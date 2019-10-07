package Backend.DBSM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author helmuthluther
 */
public class ConexionDB {
    
    private final String USUARIO = "root";
    private final String CONTRASENA = "Xela0806.";
    private final String URL = "jdbc:mysql://localhost:3306/SistemaWebRevistas";
    private Connection conexion;
    
    public ConexionDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);   
        } 
        catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos" + ex);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos" + e);
        }
    }
    
    public Connection getConexion(){
        return this.conexion;
    }
}
