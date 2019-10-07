package Backend.DBSM;

import Backend.DummyClases.Perfil;
import Backend.DummyClases.Usuario;
import Backend.Excepciones.ExcepcionRegistroUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author helmuthluther
 */
public class UsuarioDAO {
 
    private PreparedStatement declaracion;
    private ResultSet resultado;
           
    public void crearUsuario(Usuario usuario, Perfil perfil, Connection conexion) throws ExcepcionRegistroUsuario{
        try {
            conexion.setAutoCommit(false);
            declaracion = conexion.prepareStatement("INSERT INTO Usuario  VALUES (?, ?, ?);");
            declaracion.setString(1, usuario.getNombreUsuario());
            declaracion.setString(2, usuario.getContrasena());
            declaracion.setInt(3, usuario.getTipo());
            declaracion.executeUpdate();
            
            declaracion = conexion.prepareStatement("INSERT INTO Perfil VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            declaracion.setString(1, perfil.getNombreUsuario());
            declaracion.setString(2, perfil.getNombre());
            declaracion.setString(3, perfil.getApellido());
            declaracion.setString(4, perfil.getURLFotoPerfil());
            declaracion.setString(5, perfil.getHobbies());
            declaracion.setString(6, perfil.getTemasInteres());
            declaracion.setString(7, perfil.getDescripcion());
            declaracion.setString(8, perfil.getGustos());
            declaracion.executeUpdate();
            
            conexion.commit();   
            conexion.setAutoCommit(true);
        } 
        catch (SQLException ex) {
            try {
                conexion.rollback();
                conexion.setAutoCommit(true);
                throw new ExcepcionRegistroUsuario("El nombre de usuario ya se encuentra registrado");
            }
            catch (SQLException ex1) {
                System.out.println("No se pudo hacer rollback");
            }
        }    
    }
    
     public void modificarPerfil(Perfil perfil, Connection conexion){
        try {
            declaracion = conexion.prepareStatement("UPDATE Perfil SET Nombre = ?, Apellido = ?, URLFotoPerfil = ?, Hobbies = ?, TemasInteres = ?, Descripcion = ?, Gustos = ? WHERE NombreUsuario = ?;");
            declaracion.setString(1, perfil.getNombre());
            declaracion.setString(2, perfil.getApellido());
            declaracion.setString(3, perfil.getURLFotoPerfil());
            declaracion.setString(4, perfil.getHobbies());
            declaracion.setString(5, perfil.getTemasInteres());
            declaracion.setString(6, perfil.getDescripcion());
            declaracion.setString(7, perfil.getGustos());
            declaracion.setString(8, perfil.getNombreUsuario());
            declaracion.executeUpdate();
        } 
        catch (SQLException ex) {
            System.out.println("Error de conexion con la DB" + ex);
        }    
    }

    
    public int iniciarSesion(Usuario usuario, Connection conexion) throws ExcepcionRegistroUsuario{
        try {
            declaracion = conexion.prepareStatement("SELECT* FROM Usuario WHERE NombreUsuario = ?;");
            declaracion.setString(1, usuario.getNombreUsuario());
            resultado = declaracion.executeQuery();
            if(!resultado.next()){
                throw new ExcepcionRegistroUsuario("El nombre de usuario no se encuentra registrado");
            }
            else{
                if(resultado.getString("Contrasena").equals(usuario.getContrasena())){
                    return resultado.getInt("Tipo");
                }
                else{
                    throw new ExcepcionRegistroUsuario("Contrasena Incorrecta");
                }
            }       
        } 
        catch (SQLException ex) {
            System.out.println("Error con la base de datos");
        }    
        return 0;
    }
    
    public ArrayList<Usuario> obtenerUsuarios(Connection conexion) throws SQLException{
        ArrayList<Usuario> usuarios = new ArrayList<>();
        declaracion = conexion.prepareStatement("SELECT* FROM Usuario;");
        resultado = declaracion.executeQuery();
        while (resultado.next()){
            Usuario usuario = new Usuario(resultado.getString("NombreUsuario"), resultado.getString("Contrasena"));
            usuarios.add(usuario);
        }
        return usuarios;
    }
   
}
