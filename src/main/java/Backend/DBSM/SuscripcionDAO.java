package Backend.DBSM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Backend.DummyClases.Suscripcion;

/**
 *
 * @author helmuthluther
 */
public class SuscripcionDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    
    public boolean verfificarSuscripcion(Connection conexion, int idPublicacion, String nombreUsuario){
        try {
            declaracion = conexion.prepareStatement("SELECT IdSuscripcion FROM Suscribir WHERE IdPublicacion = ? && NombreUsuario = ?;");
            declaracion.setInt(1, idPublicacion);
            declaracion.setString(2, nombreUsuario);
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return true;
            }
            else{
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return false;
    }
     
    public boolean verfificarVigenciaSuscripcion(Connection conexion, int idPublicacion, String nombreUsuario){
        try {
            declaracion = conexion.prepareStatement("SELECT Activa FROM Suscribir WHERE IdPublicacion = ? && NombreUsuario = ?;");
            declaracion.setInt(1, idPublicacion);
            declaracion.setString(2, nombreUsuario);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return resultado.getBoolean("Activa");
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return false;
    }
     
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdSuscripcion FROM Suscribir ORDER BY IdSuscripcion DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdSuscripcion") + 1);
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return 0;
    }
    
    public void agregarSuscripcion(Suscripcion suscripcion, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Suscribir VALUES(?,?,?,?,?,?,?);");
            declaracion.setInt(1, suscripcion.getIdSuscripcion());
            declaracion.setInt(2, suscripcion.getIdPublicacion());
            declaracion.setString(3, suscripcion.getNombreUsuario());
            declaracion.setTimestamp(4, suscripcion.getFechaInicio());
            declaracion.setTimestamp(5, suscripcion.getFechaFin());
            declaracion.setBoolean(6, suscripcion.isActiva());
            declaracion.setTimestamp(7, suscripcion.getFechaSuscripcion());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }   
    }
    
    public int obtenerId(Connection conexion, int idPublicacion, String nombreUsuario){
        try {
            declaracion = conexion.prepareStatement("SELECT IdSuscripcion FROM Suscribir WHERE IdPublicacion = ? && NombreUsuario = ?;");
            declaracion.setInt(1, idPublicacion);
            declaracion.setString(2, nombreUsuario);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return (resultado.getInt("IdSuscripcion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS"+ ex);
        }
        return 0;
    }
    
    public void actualizarSuscripcion(Suscripcion suscripcion, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("UPDATE Suscribir SET FechaInicio = ?, FechaFin = ?, Activa = ? WHERE IdSuscripcion = ? && NombreUsuario = ?;");
            declaracion.setTimestamp(1, suscripcion.getFechaInicio());
            declaracion.setTimestamp(2, suscripcion.getFechaFin());
            declaracion.setBoolean(3, suscripcion.isActiva());
            declaracion.setInt(4, suscripcion.getIdSuscripcion());
            declaracion.setString(5, suscripcion.getNombreUsuario());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }   
    }
    
    
    
}
