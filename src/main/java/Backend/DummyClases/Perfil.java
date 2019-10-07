package Backend.DummyClases;

import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author helmuthluther
 */
public class Perfil {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String URLFotoPerfil;
    private String hobbies;
    private String temasInteres;
    private String descripcion;
    private String gustos;
    
    public Perfil(HttpServletRequest request){
        this.nombreUsuario = request.getParameter("nombreUsuario");
        this.nombre = request.getParameter("nombre");
        this.apellido = request.getParameter("apellido");
        this.URLFotoPerfil = request.getParameter("URLFotoPerfil");
        this.hobbies = request.getParameter("hobbies");
        this.temasInteres = request.getParameter("temasInteres");
        this.descripcion = request.getParameter("descripcion");
        this.gustos = request.getParameter("gustos");
    }

    public Perfil(String nombreUsuario, String nombre, String apellido, String URLFotoPerfil, String hobbies, String temasInteres, String descripcion, String gustos) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.URLFotoPerfil = URLFotoPerfil;
        this.hobbies = hobbies;
        this.temasInteres = temasInteres;
        this.descripcion = descripcion;
        this.gustos = gustos;
    }
    
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getURLFotoPerfil() {
        return URLFotoPerfil;
    }

    public void setURLFotoPerfil(String URLFotoPerfil) {
        this.URLFotoPerfil = URLFotoPerfil;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getTemasInteres() {
        return temasInteres;
    }

    public void setTemasInteres(String temasInteres) {
        this.temasInteres = temasInteres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGustos() {
        return gustos;
    }

    public void setGustos(String gustos) {
        this.gustos = gustos;
    }
    
}
