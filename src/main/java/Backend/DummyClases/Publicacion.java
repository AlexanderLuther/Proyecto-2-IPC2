package Backend.DummyClases;

import java.sql.Timestamp;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author helmuthluther
 */
public class Publicacion {
    
    private int idPublicacion;
    private int idRevista;
    private String nombrePublicacion;
    private String nombreUsuario;
    private Timestamp fecha;
    private double cuotaSuscripcion;

    public Publicacion(HttpServletRequest request, int tipo) {
        if(tipo == 1){
            this.idPublicacion = Integer.parseInt(request.getAttribute("idPublicacion").toString());
            this.cuotaSuscripcion = Double.parseDouble(request.getParameter("cuotaSuscripcion"));
            this.nombrePublicacion = request.getParameter("nombrePublicacion");
        }
        else{
            this.idPublicacion = Integer.parseInt(request.getParameter("idPublicacion"));
            this.cuotaSuscripcion = Double.parseDouble(request.getAttribute("cuotaSuscripcion").toString());
            this.nombrePublicacion = request.getAttribute("nombrePublicacion").toString();
        }
        this.idRevista = Integer.parseInt(request.getAttribute("idRevista").toString());
        this.nombreUsuario = request.getSession().getAttribute("usuario").toString(); 
        this.fecha = Timestamp.valueOf(request.getAttribute("fecha").toString());
    }

    public Publicacion(int idPublicacion, int idRevista, String nombrePublicacion, String nombreUsuario, Timestamp fecha, double cuotaSuscripcion) {
        this.idPublicacion = idPublicacion;
        this.idRevista = idRevista;
        this.nombrePublicacion = nombrePublicacion;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.cuotaSuscripcion = cuotaSuscripcion;
    }
    
    
    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }


    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public double getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(double cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }
    
    
}
