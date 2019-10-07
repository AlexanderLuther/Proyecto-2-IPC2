package Backend.DBSM;

import Backend.DummyClases.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author helmuthluther
 */
public class PerfilDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    private Perfil perfil;
    
     public Perfil obtenerPerfil(String nombreUsuario, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Perfil WHERE NombreUsuario = ?;");
            declaracion.setString(1, nombreUsuario);
            resultado = declaracion.executeQuery();
            while (resultado.next()){
                perfil = new Perfil(resultado.getString("NombreUsuario"), resultado.getString("Nombre"), resultado.getString("Apellido"), resultado.getString("URLFotoPerfil"),
                                    resultado.getString("Hobbies"), resultado.getString("TemasInteres"), resultado.getString("Descripcion"), resultado.getString("Gustos"));
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos");
        }
        return perfil;
    }
}
