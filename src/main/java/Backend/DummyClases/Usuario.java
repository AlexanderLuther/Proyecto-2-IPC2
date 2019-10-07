package Backend.DummyClases;

import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author helmuthluther
 */
public class Usuario {
    
    private String nombreUsuario;
    private String contrasena;
    private int tipo;

    public Usuario(HttpServletRequest request){
        this.nombreUsuario = request.getParameter("nombreUsuario");
        this.contrasena = request.getParameter("contrasena");
        this.tipo = Integer.parseInt(request.getParameter("tipoUsuario"));
    }
    
    public Usuario(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
    
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    
}
