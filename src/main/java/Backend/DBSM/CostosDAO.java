package Backend.DBSM;

import Backend.Excepciones.ExcepcionRegistroUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Backend.DummyClases.Costo;

/**
 *
 * @author helmuthluther
 */
public class CostosDAO {

    private PreparedStatement declaracion;
    private PreparedStatement declaracion2;
    private ResultSet resultado; 
    
    public void establecerCostoGlobal(double costoGlobal, Connection conexion) throws ExcepcionRegistroUsuario{
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Cuota");
            resultado = declaracion.executeQuery();
            
            if(resultado.next()){
                declaracion2 = conexion.prepareStatement("UPDATE Cuota SET CostoGlobal = ?;");
                declaracion2.setDouble(1, costoGlobal);
            }
            else{
                declaracion2 = conexion.prepareStatement("INSERT INTO Cuota VALUES(?,?);");
                declaracion2.setDouble(1, costoGlobal);
                declaracion2.setDouble(2, 0);
            }
            declaracion2.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }    
    }
    
    public void establecerPorcentajePagoGlobal(double porcentajePago, Connection conexion) throws ExcepcionRegistroUsuario{
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Cuota");
            resultado = declaracion.executeQuery();
            
            if(resultado.next()){
                declaracion2 = conexion.prepareStatement("UPDATE Cuota SET PorcentajePago = ?;");
                declaracion2.setDouble(1, porcentajePago);
            }
            else{
                declaracion2 = conexion.prepareStatement("INSERT INTO Cuota VALUES(?,?);");
                declaracion2.setDouble(1, 0);
                declaracion2.setDouble(2, porcentajePago);               
            }
            declaracion2.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }    
    }
    
    public double obtenerCostoGlobal(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT CostoGlobal FROM Cuota;");
            resultado = declaracion.executeQuery();
            while(resultado.next()){
                return resultado.getDouble("CostoGlobal");
            }
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }
     return 0;
    }
    
    public void modificarCostoRevista(Connection conexion, double costoPorDia, int idRevista){
        try {
            declaracion = conexion.prepareStatement("UPDATE Revista SET CostoPorDIa = ? WHERE IdRevista = ?;");
            declaracion.setDouble(1, costoPorDia);
            declaracion.setInt(2, idRevista);
            declaracion.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }
    }
    
    public void guardarNuevoCosto(Connection conexion, Costo costo){
        try {
            declaracion = conexion.prepareStatement("INSERT INTO Costo VALUES(?,?,?,?);");
            declaracion.setInt(1, costo.getIdCosto());
            declaracion.setInt(2, costo.getIdRevista());
            declaracion.setDouble(3, costo.getCosto());
            declaracion.setTimestamp(4, costo.getFecha());
            declaracion.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }
    }
    
    public int obtenerNuevoId(Connection conexion){
        try {
            declaracion = conexion.prepareStatement("SELECT IdCosto FROM Costo ORDER BY IdCosto DESC LIMIT 1;");
            resultado = declaracion.executeQuery();
            if(resultado.next()){
               return (resultado.getInt("IdCosto") + 1);
            }
            else{
                return 1;
            }
        } 
        catch (SQLException ex) {
            System.out.println("Error en la conexion con la base de datos" + ex);
        }
     return 0;
    }
}
