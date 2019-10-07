package Backend.DBSM;

import Backend.DummyClases.Publicacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author helmuthluther
 */
public class PublicacionDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    private ArrayList<Publicacion> publicaciones = new ArrayList<>();
    private Publicacion publicacion;
    
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdPublicacion FROM Publicar  ORDER BY IdPublicacion DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdPublicacion") + 1);
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }
        return 0;
    }
    
    public int obtenerId(Connection conexion, int idRevista){
        try {
            declaracion = conexion.prepareStatement("SELECT IdPublicacion FROM Publicar WHERE IdRevista = ?;");
            declaracion.setInt(1, idRevista);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return (resultado.getInt("IdPublicacion"));
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return 0;
    }
    
    public void agregarPublicacion(Publicacion publicacion, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Publicar VALUES(?,?,?,?,?,?);");
            declaracion.setInt(1, publicacion.getIdPublicacion());
            declaracion.setInt(2, publicacion.getIdRevista());
            declaracion.setString(3, publicacion.getNombrePublicacion());
            declaracion.setString(4, publicacion.getNombreUsuario());
            declaracion.setTimestamp(5, publicacion.getFecha());
            declaracion.setDouble(6, publicacion.getCuotaSuscripcion());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }   
    }
    
    public ArrayList<Publicacion> obtenerPublicaciones(Connection conexion, String nombreUsuario){
        try {
            publicaciones.clear();
            declaracion = conexion.prepareStatement("SELECT DISTINCT IdPublicacion, NombrePublicacion FROM Publicar WHERE NombreUsuario = ?;");
            declaracion.setString(1, nombreUsuario);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                publicacion = new Publicacion(resultado.getInt("IdPublicacion"), 0, resultado.getString("NombrePublicacion"),null, null, 0);
                publicaciones.add(publicacion);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return publicaciones;
    }
    
    public double obtenerCuotaSuscripcion(Connection conexion, int idPublicacion){
        try {
            declaracion = conexion.prepareStatement("SELECT CuotaSuscripcion FROM Publicar WHERE IdPublicacion = ?;");
            declaracion.setInt(1, idPublicacion);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return resultado.getInt("CuotaSuscripcion");
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }
        return 0;
    }
    
    public String obtenerNombrePublicacion(Connection conexion, int idPublicacion){
        try {
            declaracion = conexion.prepareStatement("SELECT NombrePublicacion FROM Publicar WHERE IdPublicacion = ?;");
            declaracion.setInt(1, idPublicacion);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return resultado.getString("NombrePublicacion");
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }
        return "Vacio";
    }
    
}
