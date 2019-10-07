package Backend.DummyClases;

import java.sql.Timestamp;
import java.time.Instant;
import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author helmuthluther
 */
public class Suscripcion {
    
    private int idSuscripcion;
    private int idPublicacion;
    private String nombreUsuario;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private boolean activa;
    private Timestamp fechaSuscripcion;

    public Suscripcion(int idSuscripcion, int idPublicacion, String nombreUsuario, Timestamp fechaInicio, Timestamp fechaFin, boolean activa, Timestamp fechaSuscripcion) {
        this.idSuscripcion = idSuscripcion;
        this.idPublicacion = idPublicacion;
        this.nombreUsuario = nombreUsuario;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.activa = activa;
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    public Suscripcion(HttpServletRequest request) {
        this.idSuscripcion = Integer.parseInt(request.getAttribute("idSuscripcion").toString());
        this.idPublicacion = Integer.parseInt(request.getAttribute("idPublicacion").toString());
        this.nombreUsuario = request.getSession().getAttribute("usuario").toString();
        this.fechaInicio = Timestamp.valueOf(request.getAttribute("fechaSuscripcion").toString());
        this.fechaFin = Timestamp.valueOf(request.getAttribute("fechaFin").toString());
        if(request.getAttribute("activa").equals(true)) this.activa = true;
        else this.activa = false;
        this.fechaSuscripcion = Timestamp.valueOf(request.getAttribute("fechaSuscripcion").toString());;
    }
    
    public int getIdSuscripcion() {
        return idSuscripcion;
    }

    public void setIdSuscripcion(int idSuscripcion) {
        this.idSuscripcion = idSuscripcion;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Timestamp getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Timestamp fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }
    
    
    
}
