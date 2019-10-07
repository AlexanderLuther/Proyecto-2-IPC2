package Backend.DBSM;

import Backend.DummyClases.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author helmuthluther
 */
public class RevistaDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    private ResultSet resultado1;
    private ResultSet resultado2;
    private ArrayList<Revista> revistas = new ArrayList<>();
    private Revista revista;
    
    public ArrayList<Revista> obtenerRevistasAdministrador(Connection conexion){
        revistas.clear();
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Revista");
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                revista = new Revista(resultado.getInt("IdRevista"), resultado.getString("NombreUsuarioAutor"), resultado.getString("Nombre"), resultado.getString("URLPDF"),
                          resultado.getString("Descripcion"), resultado.getString("Autor"), resultado.getInt("Volumen"), resultado.getDouble("CostoPorDIa"), resultado.getDouble("CuotaSuscripcion"),
                          resultado.getBoolean("BloquearComentarios"), resultado.getBoolean("BloquearMeGusta"), resultado.getBoolean("BloquearSuscripciones"), resultado.getTimestamp("FechaCreacion"));
                revistas.add(revista);
            }   
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS"); 
        }
    return revistas;
    }
    
    public ArrayList<Revista> obtenerRevistasEditor(Connection conexion, String nombreUsuario){
        revistas.clear();
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Revista WHERE NombreUsuarioAutor = ?;");
            declaracion.setString(1, nombreUsuario);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                revista = new Revista(resultado.getInt("IdRevista"), resultado.getString("NombreUsuarioAutor"), resultado.getString("Nombre"), resultado.getString("URLPDF"),
                          resultado.getString("Descripcion"), resultado.getString("Autor"), resultado.getInt("Volumen"), resultado.getDouble("CostoPorDIa"), resultado.getDouble("CuotaSuscripcion"),
                          resultado.getBoolean("BloquearComentarios"), resultado.getBoolean("BloquearMeGusta"), resultado.getBoolean("BloquearSuscripciones"), resultado.getTimestamp("FechaCreacion"));
                revistas.add(revista);
            }   
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS"); 
        }
    return revistas;
    }
    
    public ArrayList<Revista> obtenerRevistasSuscripciones(Connection conexion, String nombreUsuario){
        revistas.clear();
        try {
            //Obtener todos los idPublicacion a los cuales esta suscrito el usuario
            declaracion = conexion.prepareStatement("SELECT IdPublicacion FROM Suscribir WHERE NombreUsuario = ?;");
            declaracion.setString(1, nombreUsuario);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                //Obtener todos los idRevista
                declaracion = conexion.prepareStatement("SELECT IdRevista FROM Publicar WHERE IdPublicacion = ?;");
                declaracion.setInt(1, resultado.getInt("IdPublicacion"));
                resultado1 = declaracion.executeQuery();
                while(resultado1.next()){
                    declaracion = conexion.prepareStatement("SELECT* FROM Revista WHERE IdRevista = ?;");
                    declaracion.setInt(1, resultado1.getInt("IdRevista"));
                    resultado2 = declaracion.executeQuery();
                    while(resultado2.next()){
                        revista = new Revista(resultado2.getInt("IdRevista"), resultado2.getString("NombreUsuarioAutor"), resultado2.getString("Nombre"), resultado2.getString("URLPDF"),
                        resultado2.getString("Descripcion"), resultado2.getString("Autor"), resultado2.getInt("Volumen"), resultado2.getDouble("CostoPorDIa"), resultado2.getDouble("CuotaSuscripcion"),
                        resultado2.getBoolean("BloquearComentarios"), resultado2.getBoolean("BloquearMeGusta"), resultado2.getBoolean("BloquearSuscripciones"), resultado2.getTimestamp("FechaCreacion"));
                        revistas.add(revista);
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex); 
        }
    return revistas;
    }
    
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdRevista FROM Revista  ORDER BY IdRevista DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdRevista") + 1);
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return 0;
    }
    
    public void agregarRevista(Revista revista, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Revista VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);");
            declaracion.setInt(1, revista.getIdRevista());
            declaracion.setString(2, revista.getNombreUsuarioAutor());
            declaracion.setString(3, revista.getNombre());
            declaracion.setString(4, revista.getURLPDF());
            declaracion.setString(5, revista.getDescripcion());
            declaracion.setString(6, revista.getAutor());
            declaracion.setInt(7, revista.getVolumen());
            declaracion.setDouble(8, revista.getCostoPorDia());
            declaracion.setDouble(9, revista.getCuotaSuscripcion());
            declaracion.setBoolean(10, revista.isBloquearComentarios());
            declaracion.setBoolean(11, revista.isBloquearMeGusta());
            declaracion.setBoolean(12, revista.isBloquearSuscripciones());
            declaracion.setTimestamp(13, revista.getFechaCreacion());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }   
    }
    
    public Revista obtenerRevista(Connection conexion, int idRevista){
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Revista WHERE IdRevista = ?;");
            declaracion.setInt(1, idRevista);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                revista = new Revista(resultado.getInt("IdRevista"), resultado.getString("NombreUsuarioAutor"), resultado.getString("Nombre"), resultado.getString("URLPDF"),
                          resultado.getString("Descripcion"), resultado.getString("Autor"), resultado.getInt("Volumen"), resultado.getDouble("CostoPorDIa"), resultado.getDouble("CuotaSuscripcion"),
                          resultado.getBoolean("BloquearComentarios"), resultado.getBoolean("BloquearMeGusta"), resultado.getBoolean("BloquearSuscripciones"), resultado.getTimestamp("FechaCreacion"));
            }   
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS"); 
        }
    return revista;
    }
    
    public void modificarRevista(Revista revista, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("UPDATE Revista SET BloquearComentarios = ?, BloquearMeGusta = ?, BloquearSuscripciones = ? WHERE IdRevista = ?;");
            declaracion.setBoolean(1, revista.isBloquearComentarios());
            declaracion.setBoolean(2, revista.isBloquearMeGusta());
            declaracion.setBoolean(3, revista.isBloquearSuscripciones());
            declaracion.setInt(4, revista.getIdRevista());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }   
    }
    
    
}
