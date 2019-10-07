package Backend.DBSM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Backend.DummyClases.Marcador;

/**
 *
 * @author helmuthluther
 */
public class MarcadorDAO {
    
    private PreparedStatement declaracion;
    private ResultSet resultado;
    private Marcador marcador;
    
    public void guardarMarcador(Marcador marcador, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Marcador VALUES(?,?,?);");
            declaracion.setInt(1, marcador.getIdRevista() );
            declaracion.setString(2, marcador.getContenido());
            declaracion.setInt(3, marcador.getTipo());
            declaracion.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos");
        }
    }
    
    public Marcador obtenerMarcador(Connection conexion, int idRevista, int tipo){
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Marcador WHERE IdRevista = ? && Tipo = ?;");
            declaracion.setInt(1, idRevista);
            declaracion.setInt(2, tipo);
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                marcador = new Marcador(resultado.getInt("IdRevista"), resultado.getString("Contenido"), resultado.getInt("Tipo"));
            }
        } catch (SQLException ex) {
            System.out.println("Error con la base de datos");
        }
        return marcador;
    }
    
    
    
    
}
