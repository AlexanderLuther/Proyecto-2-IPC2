package Backend.DBSM;

import Backend.DummyClases.Revista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Backend.DummyClases.Comentario;
import java.util.ArrayList;
/**
 *
 * @author helmuthluther
 */
public class ComentarioDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    private ArrayList<Comentario> comentarios = new ArrayList<>();
    private Comentario comentario;
    
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdComentario FROM Comentario ORDER BY IdComentario DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdComentario") + 1);
            }
            else{
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS" + ex);
        }
        return 0;
    }
    
    public void agregarComentario(Comentario comentario, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Comentario VALUES(?,?,?,?,?);");
            declaracion.setInt(1, comentario.getIdRevista());
            declaracion.setInt(2, comentario.getIdComentario());
            declaracion.setString(3, comentario.getNombreUsuario());
            declaracion.setString(4, comentario.getComentario());
            declaracion.setTimestamp(5, comentario.getFecha());
            declaracion.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS");
        }   
    }
    
     public ArrayList<Comentario> obtenerComentarios(Connection conexion, int idRevista){
        comentarios.clear();
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Comentario WHERE IdRevista = ?");
            declaracion.setInt(1, idRevista);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                comentario = new Comentario(resultado.getInt("IdRevista"), resultado.getInt("IdComentario"), resultado.getString("NombreUsuario"), resultado.getString("Comentario"),resultado.getTimestamp("Fecha"));
                comentarios.add(comentario);
            }   
        } catch (SQLException ex) {
            System.out.println("Error de conexion con el DBMS"); 
        }
    return comentarios;
    }
}
